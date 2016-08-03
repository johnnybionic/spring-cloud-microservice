package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * The one and only controller. Returns words obtained
 * from the config server.
 *
 * Created by johnny on 03/08/2016.
 */

@RestController
@Slf4j
public class MainController {

    @Value("${lucky-word}")
    private String luckyWord;

    @Value("${another-word}")
    private String anotherWord;

    @Value("${spring.data.entry.one}")
    private String otherEntry;

    @RequestMapping("/words")
    public String showLuckyWord() {
        return "Today's lucky word is \"" + luckyWord + "\", and the other word is:" + anotherWord;
    }

    @PostConstruct
    public void iAmHereForDebugging() {
        log.info("Settings are: [{}] [{}] [{}]", luckyWord, anotherWord, otherEntry);
    }

}
