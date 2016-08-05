package org.johnny.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * LoadBalancedPersonController with mocks.
 *
 * Created by johnny on 05/08/2016.
 */
public class LoadBalancedPersonControllerMockingTest {

    private static final String ASSIGNMENT = "work";
    private static final String NAME = "name";
    private static final String ASSIGNMENT_PATH = "/assignment";
    private static final String PERSON_PATH = "/person";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String PERSON_SERVICE = "PERSON-SERVICE";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    @InjectMocks
    private LoadBalancedPersonController controller;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private LoadBalancerClient loadBalancer;

    @Before
    public void setUp() throws URISyntaxException {

        MockitoAnnotations.initMocks(this);

        controller.setAssignmentServiceSubPath(ASSIGNMENT_PATH);
        controller.setAssignmentServiceName(ASSIGNMENT_SERVICE);
        controller.setPersonServiceName(PERSON_SERVICE);
        controller.setPersonServiceSubPath(PERSON_PATH);

        controller.setRestTemplate(restTemplate);
        controller.finaliseSetup();

    }

    @Test
    public void thatPairingIsReturned() {

        ServiceInstance personServiceInstance = new DefaultServiceInstance(PERSON_SERVICE, HOST, PORT, true);
        ServiceInstance assignmentServiceInstance = new DefaultServiceInstance(ASSIGNMENT_SERVICE, HOST, PORT, true);

        when(loadBalancer.choose(PERSON_SERVICE)).thenReturn(personServiceInstance);
        when(restTemplate.getForObject(personServiceInstance.getUri().resolve(PERSON_PATH), String.class)).thenReturn(NAME);
        when(loadBalancer.choose(ASSIGNMENT_SERVICE)).thenReturn(assignmentServiceInstance);
        when(restTemplate.getForObject(assignmentServiceInstance.getUri().resolve(ASSIGNMENT_PATH), String.class)).thenReturn(ASSIGNMENT);

        String pairing = controller.getPairing();
        assertNotNull(pairing);
        assertEquals(String.format(AbstractPairingController.ASSIGNED_TO, NAME, ASSIGNMENT), pairing);
    }
}