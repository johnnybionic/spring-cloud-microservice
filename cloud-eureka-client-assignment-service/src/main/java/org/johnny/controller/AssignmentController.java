package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashSet;
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

    private Set<String> words;

    @RequestMapping("")
    public String getAssignment() {
        int index = ThreadLocalRandom.current().nextInt(words.size());
        String assignment = (String) words.toArray()[index];
        log.info("Assignment is [{}]", assignment);
        return assignment;
    }

    @PostConstruct
    public void setWords() {
        words = new HashSet<>();
        words.add("cooking");
        words.add("washing");
        words.add("cleaning");
        words.add("mopping");
        words.add("dusting");
    }
}
