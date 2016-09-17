package org.johnny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudEurekaClientAssignmentServiceApplication {

    /**
     * Entry point.
     *
     * @param args command line
     */
    public static void main(final String[] args) {
        SpringApplication.run(CloudEurekaClientAssignmentServiceApplication.class, args);
    }
}
