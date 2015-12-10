package de.animaltest.util.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
@ApplicationScoped
public class UriHelper {

    public URI getUri(Class<?> clazz, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(clazz)
                .build();
    }

    public URI getUri(Class<?> clazz, String methodName, Long id, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(clazz)
                .path(clazz, methodName)
                .build(id);
    }
}
