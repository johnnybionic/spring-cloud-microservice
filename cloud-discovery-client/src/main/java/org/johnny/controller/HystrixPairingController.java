package org.johnny.controller;

import org.johnny.service.HystrixAssignmentService;
import org.johnny.service.HystrixPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * A controller that demonstrates Hystrix fallback.
 *
 * https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica
 *
 * Created by johnny on 05/08/2016.
 */
@RestController
@RequestMapping("/person/hy")
public class HystrixPairingController extends AbstractPairingController {

    private final HystrixPersonService personService;

    private final HystrixAssignmentService assignmentService;

    @Autowired
    public HystrixPairingController(HystrixPersonService personService, HystrixAssignmentService assignmentService) {
        this.personService = personService;
        this.assignmentService = assignmentService;
    }

    @Override
    protected String service(String service, URI path) {
        if (service.equals(personServiceName)) {
            return personService.getName();
        }
        else {
            return assignmentService.getAssignment();
        }
    }
}
