package org.johnny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudConfigServerApplication {

    /**
     * Entry point.
     *
     * @param args command line
     */
    public static void main(final String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }
}
