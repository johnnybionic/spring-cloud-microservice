package org.johnny.service;

import org.johnny.service.ConfigurationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests the ConfigurationService.
 *
 * Created by johnny on 07/08/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationServiceTest {

    @Autowired
    private ConfigurationService configurationService;

    @Test
    public void thatAssignmentsAreLoadedAsCollection() {
        Set<String> assignments = configurationService.getAssignments();
        assertNotNull(assignments);
    }
}