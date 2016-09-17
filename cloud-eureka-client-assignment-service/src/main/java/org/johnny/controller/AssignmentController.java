package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.johnny.config.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple controller to return random assignments.
 * <p>
 * Created by johnny on 04/08/2016.
 */
@Slf4j
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final ApplicationConfiguration configurationService;

    @Autowired
    public AssignmentController(final ApplicationConfiguration configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Retrieve all assignments and pick one at random.
     *
     * @return the chosen assignment
     */
    @RequestMapping("")
    public String getAssignment() {
        final Set<String> assignments = configurationService.getAssignments();
        final int index = ThreadLocalRandom.current().nextInt(assignments.size());
        final String assignment = (String) assignments.toArray()[index];
        log.info("Assignment is [{}], random was {}", assignment, index);
        return assignment;
    }

}
