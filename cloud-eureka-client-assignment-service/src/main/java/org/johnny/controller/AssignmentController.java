package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.johnny.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple controller to return random assignments.
 *
 * Created by johnny on 04/08/2016.
 */
@Slf4j
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final ConfigurationService configurationService;

    @Autowired
    public AssignmentController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @RequestMapping("")
    public String getAssignment() {
        Set<String> assignments = configurationService.getAssignments();
        int index = ThreadLocalRandom.current().nextInt(assignments.size());
        String assignment = (String) assignments.toArray()[index];
        log.info("Assignment is [{}]", assignment);
        return assignment;
    }

}
