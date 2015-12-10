package de.animaltest.animalverwaltung.rest;

import de.animaltest.animalverwaltung.domain.Animal;
import de.animaltest.animalverwaltung.service.AnimalService;
import de.animaltest.util.rest.NotFoundException;
import de.animaltest.util.rest.UriHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

import static de.animaltest.animalverwaltung.domain.Animal.NAME_PATTERN;
import static de.animaltest.util.Constants.*;
import static javax.ws.rs.core.MediaType.*;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
@Stateless
@Path("/animals")
@Produces({APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5"})
@Consumes
public class AnimalRessource {

    private static final String ANIMAL_ID_PATH_PARAM = "animalId";
    private static final String ANIMAL_NAME_QUERY_PARAM = "name";

    private static final String NOT_FOUND_ID = "animal.notFound.id";
    private static final String NOT_FOUND_NAME = "animal.notFound.name";
    private static final String NOT_FOUND_ALL = "animal.notFound.all";


    @Context
    private UriInfo uriInfo;

    @Inject
    private UriHelper uriHelper;

    @EJB
    private AnimalService as;

    @GET
    @Produces({TEXT_PLAIN, APPLICATION_JSON})
    @Path("version")
    public String getVersion() {
        return "1.0";
    }

    @GET
    @Path("{" + ANIMAL_ID_PATH_PARAM + ":[1-9][0-9]*}")
    public Response findAnimalsById(@PathParam(ANIMAL_ID_PATH_PARAM) Long id) {
        final Animal animal = as.findAnimalsById(id);
        if (animal == null) {
            throw new NotFoundException(NOT_FOUND_ID, id);
        }

        return Response.ok(animal)
                .links(getTransitionalLinks(animal, uriInfo))
                .build();

    }

    public URI getUriAnimal(Animal animal, UriInfo uriInfo) {
        return uriHelper.getUri(AnimalRessource.class, "findAnimalsById", animal.getId(), uriInfo);
    }

    public Link[] getTransitionalLinks(Animal animal, UriInfo uriInfo) {
        final Link self = Link.fromUri(getUriAnimal(animal, uriInfo))
                .rel(SELF_LINK)
                .build();

        final Link add = Link.fromUri(getUriAnimal(animal, uriInfo))
                .rel(ADD_LINK)
                .build();

        final Link update = Link.fromUri(getUriAnimal(animal, uriInfo))
                .rel(UPDATE_LINK)
                .build();

        final Link remove = Link.fromUri(getUriAnimal(animal, uriInfo))
                .rel(REMOVE_LINK)
                .build();

        return new Link[]{self, add, update, remove};
    }

    @GET
    public Response findAnimals(@QueryParam(ANIMAL_NAME_QUERY_PARAM)
                                @Pattern(regexp = NAME_PATTERN, message = "{animal.name.pattern}") String name) {
        List<? extends Animal> animals = null;
        if (name != null) {
            animals = as.findAnimalsByName(name);
            if (animals.isEmpty()) {
                throw new NotFoundException(NOT_FOUND_NAME, name);
            }
        } else {
            animals = as.findAllAnimals();
            if (animals.isEmpty()) {
                throw new NotFoundException(NOT_FOUND_ALL);
            }
        }

        return Response.ok(new GenericEntity<List<? extends Animal>>(animals) {
        })
                .links(getTransitionalLinksAnimals(animals, uriInfo))
                .build();
    }

    private Link[] getTransitionalLinksAnimals(List<? extends Animal> animals, UriInfo uriInfo) {
        if (animals == null || animals.isEmpty()) {
            return null;
        }

        final Link first = Link.fromUri(getUriAnimal(animals.get(0), uriInfo))
                .rel(FIRST_LINK)
                .build();
        final int lastPos = animals.size() - 1;
        final Link last = Link.fromUri(getUriAnimal(animals.get(lastPos), uriInfo))
                .rel(LAST_LINK)
                .build();

        return new Link[]{first, last};
    }

    @POST
    @Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML})
    @Produces
    public Response createAnimal(@Valid Animal animal) {
        animal = as.createAnimal(animal);

        return Response.created(getUriAnimal(animal, uriInfo))
                .build();
    }

    @PUT
    @Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML})
    @Produces
    public void updateAnimal(@Valid Animal animal) {
        as.updateAnimal(animal);
    }

    @DELETE
    @Path("{id:[1-9][0-9]*}")
    @Produces
    public void deleteAnimal(@PathParam("id") Long animalId) {
        as.deleteAnimal(animalId);
    }

}
