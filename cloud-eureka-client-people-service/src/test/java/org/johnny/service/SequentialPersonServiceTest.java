package org.johnny.service;

/**
 * Tests the implementation that retrieves the Person entities in order.
 *
 * Created by johnny on 16/09/2016.
 */

import org.johnny.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests the service that returns people in order (next in line).
 * <p>
 * This is an integration test, as a real repository is used.
 * <p>
 * Created by johnny on 16/09/2016.
 */
@ActiveProfiles({"junit", "sequential"})
@RunWith(SpringRunner.class)
@SpringBootTest
// the scripts are run before/after each test method. Ensures database is exactly as required, and tests won't interfere with each other
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
public class SequentialPersonServiceTest {

    private static Long testIds[] = {99L, 100L, 101L};
    @Autowired
    private PersonService personService;

    /**
     * Go through the loop a couple of times - the results should be in a determined order.
     */
    @Test
    public void thatNextPersonIsRetrieved() {
        for (int loop = 0; loop < 3; loop++) {
            for (Long testId : testIds) {
                Person person = personService.getPerson();
                assertNotNull(person);
                assertEquals((long) testId, (long) person.getId());
            }
        }
    }

    /**
     * Ensure this goes round at least once, otherwise it can mess up the test above.
     */
    @Test
    public void thatNextPersonNameIsRetrieved() {
        for (Long testId : testIds) {
            String name = personService.getName();
            assertNotNull(name);
        }
    }

}
