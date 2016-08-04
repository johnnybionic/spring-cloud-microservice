package org.johnny.repository;

import static org.junit.Assert.*;

import org.johnny.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Tests the PersonRepository.
 * 
 * Created by johnny on 04/08/2016.
 */
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@SpringBootTest
// the scripts are run before/after each test method. Ensures database is exactly as required, and tests won't interfere with each other
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
public class PersonRepositoryTest {

    private static final String LAST_NAME = "Smith";
    private static final String FIRST_NAME = "Donald";

    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void thatPersonIsSavedAndRetrieved() {

        Person person = new Person(FIRST_NAME, LAST_NAME);
        Person save = personRepository.save(person);

        person = personRepository.findOne(save.getId());
        assertNotNull(person);
        assertNotEquals(0L, (long)person.getId()); // it's probably 102, but can't be certain
        assertEquals(FIRST_NAME, person.getFirstName());
        assertEquals(LAST_NAME, person.getLastName());

        assertEquals(4, personRepository.count());
    }

    /**
     * In order to get a random Person, get all IDs and pick one.
     */
    @Test
    public void thatAllIdsAreRetrieved() {
        List<Long> allIds = personRepository.findAllIds();
        assertEquals(3, allIds.size());
    }
}
