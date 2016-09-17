package org.johnny.service;

import org.johnny.domain.Person;
import org.johnny.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service to implement Person operations.
 * This implementation returns a randomly chosen person.
 * <p>
 * Created by johnny on 04/08/2016.
 */
@Service
@Profile("!sequential")
public class RandomPersonService implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public RandomPersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Gets a random person by retrieving all and selecting one at random.
     *
     * @return the {@link Person}
     */
    @Override
    public Person getPerson() {
        final List<Long> allIds = personRepository.findAllIds();
        final int index = ThreadLocalRandom.current().nextInt(allIds.size());
        final Long randomId = allIds.get(index);
        return personRepository.findOne(randomId);
    }

    /**
     * Gets a random name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        final Person person = getPerson();
        return person.getFullName();
    }
}
