package org.johnny.controller;

import org.johnny.feign.AssignmentClient;
import org.johnny.feign.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Controller using Feign.
 *
 * Created by johnny on 04/08/2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/person/fc")
public class FeignPairingController extends AbstractPairingController {

    private final AssignmentClient assignmentClient;
    private final PersonClient personClient;

    @Autowired
    public FeignPairingController(final AssignmentClient assignmentClient,
                                  final PersonClient personClient) {
        this.assignmentClient = assignmentClient;
        this.personClient = personClient;
    }

    @Override
    protected String service(final String service, final URI personPath) {
        if (service.equals(this.getPersonServiceName())) {
            return personClient.getName();
        }
        else {
            return assignmentClient.getAssignment();
        }
    }
}
