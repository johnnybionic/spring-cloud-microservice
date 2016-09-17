package org.johnny;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests that the configuration loads.
 * Created by johnny on 15/09/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigPropertiesExampleTest {

    @Autowired
    private ConfigPropertiesExample propertiesExample;

    @Value("${sample.firstWord}")
    private String firstWord;

    @Test
    public void thatConfigIsLoaded() {
        final String firstWordFromConfig = propertiesExample.getFirstWord();
        assertNotNull(firstWordFromConfig);
        assertEquals(firstWordFromConfig, firstWord);
    }
}