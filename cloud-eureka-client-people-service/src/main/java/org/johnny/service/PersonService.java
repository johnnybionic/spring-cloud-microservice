package org.johnny.service;

import org.johnny.domain.Person;

/**
 * Gets a Person. The implementation determines which Person, e.g. randomly chosen, next in line etc.
 * Created by johnny on 16/09/2016.
 */
public interface PersonService {

    /**
     * Gets a {@link Person}.
     *
     * @return the Person
     */
    Person getPerson();

    /**
     * Gets the name of a Person.
     *
     * @return the name
     */
    String getName();
}
