package de.animaltest.util.rest;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2167460353602102626L;

    private final transient Object[] args;

    public NotFoundException(String msg, Object... args) {
        super(msg);
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

}
