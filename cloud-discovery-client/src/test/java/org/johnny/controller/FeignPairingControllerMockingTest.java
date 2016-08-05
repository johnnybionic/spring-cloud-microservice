package org.johnny.controller;

import org.johnny.feign.AssignmentClient;
import org.johnny.feign.PersonClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Test FeignPairingController with mocks
 * Created by johnny on 05/08/2016.
 */
public class FeignPairingControllerMockingTest {

    private static final String ASSIGNMENT = "work";
    private static final String NAME = "name";
    private static final String ASSIGNMENT_PATH = "/assignment";
    private static final String PERSON_PATH = "/person";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String PERSON_SERVICE = "PERSON-SERVICE";


    @InjectMocks
    private FeignPairingController controller;

    @Mock
    private AssignmentClient assignmentClient;
    @Mock
    private PersonClient personClient;

    @Before
    public void setUp() throws URISyntaxException {

        MockitoAnnotations.initMocks(this);

        controller.setAssignmentServiceName(ASSIGNMENT_SERVICE);
        controller.setPersonServiceName(PERSON_SERVICE);
    }

    @Test
    public void thatPairingIsReturned() {

        when(personClient.getName()).thenReturn(NAME);
        when(assignmentClient.getAssignment()).thenReturn(ASSIGNMENT);

        String pairing = controller.getPairing();
        assertNotNull(pairing);
        assertEquals(String.format(AbstractPairingController.ASSIGNED_TO, NAME, ASSIGNMENT), pairing);
    }
}