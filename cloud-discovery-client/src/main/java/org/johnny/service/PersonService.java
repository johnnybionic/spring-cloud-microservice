package org.johnny.service;

/**
 * Service to retrieve Person info. It's intended to return random information, i.e. a random name.
 * <p>
 * Created by johnny on 05/08/2016.
 */
public interface PersonService {

    /**
     * Get a name for a Person.
     *
     * @return the name
     */
    String getName();
}
