package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * The one and only controller. Returns words obtained
 * from the config server.
 * <p>
 * Note that this is marked as @RefreshScope, which means that it will pick up
 * changes to configuration when /refresh is called. In fact, the controller is lazy-loaded, and
 * won't actually get re-initialised until it's called for the first time after refresh.
 * <p>
 * It would be a good idea to move all configuration into {@link org.johnny.ConfigPropertiesExample}, and inject that
 * bean here - but the idea was to see @RefreshScope in action.
 * <p>
 * Created by johnny on 03/08/2016.
 */

@RestController
@Slf4j
@RefreshScope
public class MainController {

    private static final String SENTENCE = "Today's lucky word is \"%s\", and the other word is: %s";

    @Value("${lucky-word}")
    private String luckyWord;

    @Value("${another-word}")
    private String anotherWord;

    @Value("${spring.data.entry.one}")
    private String otherEntry;

    /**
     * Method that returns a formatted message with the words.
     *
     * @return the message
     */
    @RequestMapping("/words")
    public String showLuckyWord() {

        final String message = String.format(SENTENCE, luckyWord, anotherWord);
        log.debug(message);
        return message;
    }

    /**
     * Debugging.
     */
    @PostConstruct
    public void iAmHereForDebugging() {
        log.info("Settings are: [{}] [{}] [{}]", luckyWord, anotherWord, otherEntry);
    }

}
