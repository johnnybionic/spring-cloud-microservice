package org.johnny.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.johnny.feign.AssignmentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation using Hystrix.
 *
 * Created by johnny on 05/08/2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class HystrixAssignmentService implements AssignmentService {

    private static final String DEFAULT_ASSIGNMENT = "nothing!";

    private final AssignmentClient assignmentClient;

    @Autowired
    public HystrixAssignmentService(AssignmentClient assignmentClient) {
        this.assignmentClient = assignmentClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultAssignment")
    public String getAssignment() {
        return assignmentClient.getAssignment();
    }

    private String getDefaultAssignment() {
        return DEFAULT_ASSIGNMENT;
    }
}
