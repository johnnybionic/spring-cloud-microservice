package org.johnny.loader;

import org.johnny.domain.Person;
import org.johnny.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Loads sample data into the H2 database at runtime. Avoids being loaded during tests.
 * <p>
 * Created by johnny on 04/08/2016.
 */
@Component
@Profile("!junit")
public class SampleDataLoader {

    private final PersonRepository personRepository;

    @Autowired
    public SampleDataLoader(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Simplest way to add data after application has started up.
     */
    @PostConstruct
    public void createSamplePeople() {
        personRepository.save(new Person("John", "Davis"));
        personRepository.save(new Person("Don", "Javish"));
        personRepository.save(new Person("Harry", "Hoo"));
        personRepository.save(new Person("Arthur", "Negus"));
    }

}
