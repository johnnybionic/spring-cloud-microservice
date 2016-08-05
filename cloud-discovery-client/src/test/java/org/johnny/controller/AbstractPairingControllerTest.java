package org.johnny.controller;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Tests the template class. As it's abstract, it extends it - this
 * is an interesting alternative to mocking, which I read about years ago in an IBM document.
 *
 * Created by johnny on 05/08/2016.
 */
public class AbstractPairingControllerTest extends AbstractPairingController {

    private static final String ASSIGNMENT = "work";
    private static final String NAME = "name";
    private static final String ASSIGNMENT_PATH = "/assignment";
    private static final String PERSON_PATH = "/person";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String PERSON_SERVICE = "PERSON-SERVICE";

    private AbstractPairingController abstractPairingController;

    @Before
    public void setUp() {
        abstractPairingController = new AbstractPairingControllerTest();
        abstractPairingController.setAssignmentServiceSubPath(ASSIGNMENT_PATH);
        abstractPairingController.setAssignmentServiceName(ASSIGNMENT_SERVICE);
        abstractPairingController.setPersonServiceName(PERSON_SERVICE);
        abstractPairingController.setPersonServiceSubPath(PERSON_PATH);
    }

    @Test
    public void thatMessageIsReturned() {
        String pairing = abstractPairingController.getPairing();
        assertNotNull(pairing);
        assertEquals(String.format(ASSIGNED_TO, NAME, ASSIGNMENT), pairing);
    }

    @Test
    public void thatFinaliseSetupWorks() throws URISyntaxException {
        abstractPairingController.finaliseSetup();
        assertNotNull(abstractPairingController.getAssignmentPath());
        assertNotNull(abstractPairingController.getPersonPath());
    }

    @Override
    protected String service(String service, URI path) {
        switch (service) {
            case ASSIGNMENT_SERVICE:
                return ASSIGNMENT;
            case PERSON_SERVICE:
                return NAME;
            default:
                fail();
        }

        return null;
    }
}