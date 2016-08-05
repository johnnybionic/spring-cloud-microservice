package org.johnny.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests the controller that uses the {@link org.springframework.cloud.client.discovery.DiscoveryClient}
 *
 * Created by johnny on 05/08/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest({"server.port:0", "eureka.client.registerWithEureka:false", "eureka.client.fetchRegistry:false"})
@WebAppConfiguration
@ActiveProfiles("junit")
public class PairingControllerTest {

    private static final String ASSIGNMENT = "work";
    private static final String NAME = "name";
    private static final String ASSIGNMENT_PATH = "/assignment";
    private static final String PERSON_PATH = "/person";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String PERSON_SERVICE = "PERSON-SERVICE";

    @Autowired
    private PairingController controller;

    @Before
    public void setUp() {
        controller.setAssignmentServiceSubPath(ASSIGNMENT_PATH);
        controller.setAssignmentServiceName(ASSIGNMENT_SERVICE);
        controller.setPersonServiceName(PERSON_SERVICE);
        controller.setPersonServiceSubPath(PERSON_PATH);
    }

    /**
     * So the first step is how to get integration tests to work without using mocks ...
     *
     * Well, the settings above (in @SpringBootTest) do prevent the client from accessing Eureka, but then
     * nothing gets returned.
     *
     * Causes an exception with Eureka, doesn't cause the test to fail, but needs more investigation.
     */
    @Test
    @Ignore
    public void thatDiscoveryClientCanBeUsedInUnitTest() {
        String pairing = controller.getPairing();
        assertNotNull(pairing);
        assertEquals("\"null\" will be assigned to null", pairing);

    }
}