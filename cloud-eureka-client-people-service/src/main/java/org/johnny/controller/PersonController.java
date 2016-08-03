package org.johnny.controller;

import org.johnny.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Used by clients to retrieve information about people.
 *
 * Created by johnny on 03/08/2016.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private Set<Person> people;

    /**
     * Returns a random person.
     *
     * @return the Person
     */
    @RequestMapping("")
    public Person getPerson() {
        return getRandomPerson();
    }

    /**
     * For clients that won't know about the Person class.
     *
     * @return the name of a randomly-selected person
     */
    @RequestMapping("/name")
    public String getName() {
        return getRandomPerson().getFullName();
    }

    private Person getRandomPerson() {
        int index = ThreadLocalRandom.current().nextInt(people.size());
        return (Person) people.toArray()[index];
    }

    @PostConstruct
    public void createPeople() {
        people = new HashSet<>();
        people.add(new Person(1, "John", "Davis"));
        people.add(new Person(2, "Don", "Javish"));
        people.add(new Person(3, "Harry", "Hoo"));
        people.add(new Person(4, "Arthur", "Negus"));
    }
}
