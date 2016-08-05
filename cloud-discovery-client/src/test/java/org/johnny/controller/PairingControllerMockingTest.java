package org.johnny.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 * Uses mocking.
 *
 * Created by johnny on 05/08/2016.
 */
public class PairingControllerMockingTest {

    private static final String ASSIGNMENT = "work";
    private static final String NAME = "name";
    private static final String ASSIGNMENT_PATH = "/assignment";
    private static final String PERSON_PATH = "/person";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String PERSON_SERVICE = "PERSON-SERVICE";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    @InjectMocks
    private PairingController controller;

    @Mock
    private DiscoveryClient discoveryClient;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws URISyntaxException {

        MockitoAnnotations.initMocks(this);

        controller.setAssignmentServiceSubPath(ASSIGNMENT_PATH);
        controller.setAssignmentServiceName(ASSIGNMENT_SERVICE);
        controller.setPersonServiceName(PERSON_SERVICE);
        controller.setPersonServiceSubPath(PERSON_PATH);

        controller.finaliseSetup();
        controller.setRestTemplate(restTemplate); // I'm not too happy about this
    }

    @Test
    public void thatPairingIsReturned() {
        List<ServiceInstance> personServiceList = new LinkedList<>();
        ServiceInstance personServiceInstance = new DefaultServiceInstance(PERSON_SERVICE, HOST, PORT, true);
        personServiceList.add(personServiceInstance);
        URI personServiceInstanceUri = personServiceInstance.getUri().resolve(PERSON_PATH);
        when(discoveryClient.getInstances(PERSON_SERVICE)).thenReturn(personServiceList);
        when(restTemplate.getForObject(personServiceInstanceUri, String.class)).thenReturn(NAME);

        List<ServiceInstance> assignmentServiceList = new LinkedList<>();
        ServiceInstance assignmentServiceInstance = new DefaultServiceInstance(ASSIGNMENT_SERVICE, HOST, PORT, true);
        assignmentServiceList.add(assignmentServiceInstance);
        URI assignmentServiceInstanceUri = assignmentServiceInstance.getUri().resolve(ASSIGNMENT_PATH);
        when(discoveryClient.getInstances(ASSIGNMENT_SERVICE)).thenReturn(assignmentServiceList);
        when(restTemplate.getForObject(assignmentServiceInstanceUri, String.class)).thenReturn(ASSIGNMENT);

        String pairing = controller.getPairing();
        assertNotNull(pairing);
        assertEquals(String.format(AbstractPairingController.ASSIGNED_TO, NAME, ASSIGNMENT), pairing);
    }
}
