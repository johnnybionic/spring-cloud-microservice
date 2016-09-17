package org.johnny.service;

import org.johnny.domain.Person;
import org.johnny.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to implement Person operations.
 * This implementation returns a sequentially chosen person.
 * Created by johnny on 16/09/2016.
 */
@Service
@Profile("sequential")
public class SequentialPersonService implements PersonService {

    private final PersonRepository personRepository;
    private int next = 0;

    @Autowired
    public SequentialPersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person getPerson() {
        final List<Long> allIds = personRepository.findAllIds();
        final Long id = allIds.get(next);

        next++;
        if (next >= allIds.size()) {
            next = 0;
        }

        return personRepository.findOne(id);
    }

    @Override
    public String getName() {
        final Person person = getPerson();
        return person.getFullName();
    }
}
