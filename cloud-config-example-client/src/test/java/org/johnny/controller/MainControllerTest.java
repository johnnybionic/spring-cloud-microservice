package org.johnny.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * A test of the controller that does not use any Cloud features.
 *
 * Created by johnny on 07/08/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest("spring.cloud.bootstrap.enabled=false")
public class MainControllerTest {

    @Autowired
    private MainController controller;

    @Value("${lucky-word}")
    private String luckyWord;

    @Value("${another-word}")
    private String anotherWord;

    @Test
    public void thatEverythingLoadsWithoutCloudEnabled() {

        String word = controller.showLuckyWord();
        assertNotNull(word);
        assertTrue(word.contains(luckyWord));
        assertTrue(word.contains(anotherWord));
    }
}