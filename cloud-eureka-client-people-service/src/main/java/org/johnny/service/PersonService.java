package org.johnny.service;

import org.johnny.domain.Person;
import org.johnny.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service to provide Person operations.
 *
 * Created by johnny on 04/08/2016.
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getRandomPerson() {
        List<Long> allIds = personRepository.findAllIds();
        int index = ThreadLocalRandom.current().nextInt(allIds.size());
        Long randomId = allIds.get(index);
        return personRepository.findOne(randomId);
    }

    public String getRandomName() {
        Person person = getRandomPerson();
        return person.getFullName();
    }
}
