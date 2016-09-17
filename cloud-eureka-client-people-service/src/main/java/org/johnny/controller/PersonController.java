package org.johnny.controller;

import org.johnny.domain.Person;
import org.johnny.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Used by clients to retrieve a Person.
 * <p>
 * Created by johnny on 03/08/2016.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    /**
     * Returns a person.
     *
     * @return the Person
     */
    @RequestMapping("")
    public Person getPerson() {
        return personService.getPerson();
    }

    /**
     * For clients that won't know about the Person class.
     *
     * @return the name of a randomly-selected person
     */
    @RequestMapping("/name")
    public String getName() {
        return personService.getName();
    }

}
