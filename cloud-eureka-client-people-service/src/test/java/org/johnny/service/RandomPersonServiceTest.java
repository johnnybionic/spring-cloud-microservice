package org.johnny.service;

import org.johnny.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the service that returns random people.
 * <p>
 * This is an integration test, as a real repository is used.
 * <p>
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
public class RandomPersonServiceTest {

    @Autowired
    private RandomPersonService personService;

    @Test
    public void thatRandomPersonIsRetrieved() {
        final Person person = personService.getPerson();
        assertNotNull(person);
    }

    @Test
    public void thatRandomPersonNameIsRetrieved() {
        final String name = personService.getName();
        assertNotNull(name);
    }

}