package org.johnny;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Shows how ConfigurationProperties works - reduced annotation.
 * Note that this bean is not used anywhere.
 * <p>
 * Created by johnny on 05/08/2016.
 */
@Slf4j
// IntelliJ having trouble with @Data
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sample")
public class ConfigPropertiesExample {

    private String firstWord;
    private String secondWord;
    private String thirdWord;

    /**
     * Just here to log the properties.
     */
    @PostConstruct
    public void postConstruct() {
        log.debug("in post-construct");
        log.info("ConfigurationProperties: [{}] [{}] [{}]", firstWord, secondWord, thirdWord);
    }
}
