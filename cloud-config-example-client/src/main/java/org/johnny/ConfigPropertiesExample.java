package org.johnny;

import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Shows how ConfigurationProperties works - reduced annotation
 *
 * Created by johnny on 05/08/2016.
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties(prefix = "sample")
public class ConfigPropertiesExample {

    private String firstWord;
    private String secondWord;
    private String thirdWord;

    @PostConstruct
    public void postConstruct() {
        log.debug("in post-construct");
        log.info("ConfigurationProperties: [{}] [{}] [{}]", firstWord, secondWord, thirdWord);
    }
}
