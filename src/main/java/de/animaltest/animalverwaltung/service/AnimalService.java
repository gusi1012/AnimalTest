package de.animaltest.animalverwaltung.service;

import de.animaltest.animalverwaltung.domain.Animal;
import de.animaltest.util.Mock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
@Stateless
public class AnimalService implements Serializable {
    private static final long serialVersionUID = 843162465112327829L;

    @PostConstruct
    private void postConstruct() {
        System.out.println("de.tierschutz.animalverwaltung.service.AnimalService.postConstruct()");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("de.tierschutz.animalverwaltung.service.AnimalService.preDestroy()");
    }

    @NotNull(message = "{animal.notFound.id}")
    public Animal findAnimalsById(Long id) {
        if (id == null) {
            return null;
        }

        final Animal animal = Mock.findAnimalsById(id);
        return animal;
    }

    @Size(min = 1, message = "{animal.name.size}")
    public List<Animal> findAnimalsByName(String name) {
        List<Animal> animals = Mock.findAnimalByName(name);
        return animals;
    }

    @Size(min = 1, message = "{animal.notFound.name}")
    public List<Animal> findAllAnimals() {
        final List<Animal> animals = Mock.findAllAnimals();
        return animals;
    }

    public <T extends Animal> T createAnimal(T animal) {
        if (animal == null) {
            return animal;
        }

        animal = Mock.createAnimal(animal);

        return animal;

    }

    public <T extends Animal> T updateAnimal(T animal) {
        if (animal == null) {
            return null;
        }

        Mock.updateAnimal(animal);

        return animal;
    }

    public void deleteAnimal(Long animalId) {
        final Animal animal = findAnimalsById(animalId);
        if (animal == null) {
            return;
        }

        Mock.deleteAnimal(animal);
    }
}
