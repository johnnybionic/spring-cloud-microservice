package org.johnny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaServerApplication {

    /**
     * Entry point.
     *
     * @param args command line
     */
    public static void main(final String[] args) {
        SpringApplication.run(CloudEurekaServerApplication.class, args);
    }
}
