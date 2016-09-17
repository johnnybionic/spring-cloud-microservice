package org.johnny.controller;

import org.johnny.service.HystrixAssignmentService;
import org.johnny.service.HystrixPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * A controller that demonstrates Hystrix fallback.
 * <p>
 * https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica
 * <p>
 * Created by johnny on 05/08/2016.
 */
@RestController
@RequestMapping("/person/hy")
public class HystrixPairingController extends AbstractPairingController {

    private final HystrixPersonService personService;

    private final HystrixAssignmentService assignmentService;

    @Autowired
    public HystrixPairingController(final HystrixPersonService personService,
                                    final HystrixAssignmentService assignmentService) {
        this.personService = personService;
        this.assignmentService = assignmentService;
    }

    @Override
    protected String service(final String service, final URI path) {
        if (service.equals(getPersonServiceName())) {
            return personService.getName();
        }
        else {
            return assignmentService.getAssignment();
        }
    }
}
