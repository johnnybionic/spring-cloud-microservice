package org.johnny.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Test the AssignmentController.
 *
 * Created by johnny on 04/08/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AssignmentControllerTest {

    @Autowired
    private AssignmentController assignmentController;

    @Test
    public void thatAssignmentsAreReturned() {

        String assignment = assignmentController.getAssignment();
        assertNotNull(assignment);

        assignmentController.getAssignment();
        assignmentController.getAssignment();
    }

}