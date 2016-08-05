package org.johnny.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
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
@Setter
public abstract class AbstractPairingController {

    static final String ASSIGNED_TO = "\"%s\" will be assigned to %s";

    @Value("${person.service.name}")
    protected String personServiceName;

    @Value("${person.service.subpath}")
    private String personServiceSubPath;

    @Value("${assignment.service.name}")
    protected String assignmentServiceName;

    @Value("${assignment.service.subpath}")
    private String assignmentServiceSubPath;

    @Getter
    private URI personPath;
    @Getter
    private URI assignmentPath;

    @Setter
    RestTemplate restTemplate;

    public AbstractPairingController() {
        this.restTemplate = new RestTemplate();
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

        String message = String.format(ASSIGNED_TO, person, assignment);
        log.info(message);

        return message;
    }

    private String getPerson() {
        return service(personServiceName, personPath);
    }

    private String getAssignment() {
        return service(assignmentServiceName, assignmentPath);
    }

    protected abstract String service(String service, URI path);

    /*
    * The config data is not available until after the bean has been constructed.
     */
    @PostConstruct
    public void finaliseSetup() throws URISyntaxException {
        personPath = new URI(personServiceSubPath);
        assignmentPath = new URI(assignmentServiceSubPath);
    }
}
