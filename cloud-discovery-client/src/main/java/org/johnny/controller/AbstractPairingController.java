package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * There's some shared constants and variables. Generally there
 * wouldn't be two controllers doing the same thing, but then, this is jsut a sample app ...
 *
 * Plus I was interested to see if a template controller would work. It does :)
 *
 * Created by johnny on 04/08/2016.
 */
@Slf4j
public abstract class AbstractPairingController {

    private static final String PERSON_SERVICE = "PERSON-SERVICE";
    private static final String PERSON_NAME_PATH = "/person/name";
    private static final String ASSIGNMENT_SERVICE = "ASSIGNMENT-SERVICE";
    private static final String ASSIGNMENT_PATH = "/assignments";

    final RestTemplate restTemplate;
    private URI personPath;
    private URI assignmentPath;

    public AbstractPairingController() throws URISyntaxException {
        this.restTemplate = new RestTemplate();

        // get these from configuration service
        personPath = new URI(PERSON_NAME_PATH);
        assignmentPath = new URI(ASSIGNMENT_PATH);
    }

    /**
     * Pairs a random person with a random assignment and returns the results.
     *
     * @return the result
     */
    @RequestMapping("")
    public String getPairing() {
        String person = getPerson();
        String assignment = getAssignment();

        String message = String.format("\"%s\" will be assigned to %s", person, assignment);
        log.info(message);

        return message;
    }

    private String getPerson() {
        return service(PERSON_SERVICE, personPath);
    }

    private String getAssignment() {
        return service(ASSIGNMENT_SERVICE, assignmentPath);
    }

    protected abstract String service(String personService, URI personPath);

}
