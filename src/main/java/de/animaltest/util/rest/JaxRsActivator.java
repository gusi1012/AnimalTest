package de.animaltest.util.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static de.animaltest.util.Constants.REST_PATH;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
@ApplicationPath(REST_PATH)
public class JaxRsActivator extends Application {
}
