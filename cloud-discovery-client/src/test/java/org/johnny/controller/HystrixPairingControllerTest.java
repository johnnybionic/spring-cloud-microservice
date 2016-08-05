package org.johnny.controller;

import org.johnny.service.HystrixAssignmentService;
import org.johnny.service.HystrixPersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests the HystrixPairingController.
 *
 * Created by johnny on 05/08/2016.
 */
public class HystrixPairingControllerTest {

    private static final String ASSIGNMENT = "work";
    private static final String NAME = "name";
    private static final String ASSIGNMENT_PATH = "/assignment";
    private static final String PERSON_PATH = "/person";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String PERSON_SERVICE = "PERSON-SERVICE";

    @InjectMocks
    private HystrixPairingController controller;

    @Mock
    private HystrixPersonService personService;

    @Mock
    private HystrixAssignmentService assignmentService;

    @Before
    public void setUp() throws URISyntaxException {

        MockitoAnnotations.initMocks(this);

        controller.setAssignmentServiceName(ASSIGNMENT_SERVICE);
        controller.setPersonServiceName(PERSON_SERVICE);
    }


    @Test
    public void thatPairingIsReturned() {

        when(personService.getName()).thenReturn(NAME);
        when(assignmentService.getAssignment()).thenReturn(ASSIGNMENT);

        String pairing = controller.getPairing();

        verify(personService).getName();
        verify(assignmentService).getAssignment();

        assertNotNull(pairing);
        assertEquals(String.format(AbstractPairingController.ASSIGNED_TO, NAME, ASSIGNMENT), pairing);
    }
}