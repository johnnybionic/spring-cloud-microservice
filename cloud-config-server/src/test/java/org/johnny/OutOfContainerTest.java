package org.johnny;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Serves configuration from test resources.
 *
 * Created by johnny on 03/08/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CloudConfigServerApplication.class)
@WebAppConfiguration
@ActiveProfiles("native")	//	"native" means use local classpath location rather than GitHub.
public class OutOfContainerTest {

    @Autowired
    private WebApplicationContext spring;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(spring).build();
    }

    /**
     * To test is the config server is working, this simulates a call from
     * a client for the configuration of "testConfig", using the default
     * profile. These configuration files are on the classpath, and the server
     * is running the 'native' profile - this causes the server to use a
     * file backend instead of Git.
     * (application.yml and testConfig.yml)
     * @throws Exception
     */
    @Test
    public void propertyLoadTest() throws Exception {

        MvcResult result =
                mockMvc.perform(get("/testConfig-default.properties"))
                        .andExpect(status().isOk())
                        .andReturn()
                ;

        String returned = result.getResponse().getContentAsString();

        //	Check that the test values from the yml are present in the properties:
        assertTrue(returned.contains("fromApplication:"));
        assertTrue(returned.contains("applicationValue"));
        assertTrue(returned.contains("fromTestConfig:"));
        assertTrue(returned.contains("testConfigValue"));
    }

    /**
     * Simulates the client requesting a non-existent profile, which
     * should cause the server to fall back to the default.
     *
     * @throws Exception
     */
    @Test
    public void badProfile() throws Exception {

        MvcResult result =
                mockMvc.perform(get("/testConfig-notthere.properties"))
                        .andExpect(status().isOk())
                        .andReturn()
                ;

        String returned = result.getResponse().getContentAsString();
        assertTrue(returned.contains("fromApplication:"));
        assertTrue(returned.contains("applicationValue"));
        assertTrue(returned.contains("fromTestConfig:"));
        assertTrue(returned.contains("testConfigValue"));
    }
}
