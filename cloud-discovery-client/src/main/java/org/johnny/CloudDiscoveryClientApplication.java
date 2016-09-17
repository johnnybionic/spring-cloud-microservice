package org.johnny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
public class CloudDiscoveryClientApplication {

    /**
     * Entry point.
     *
     * @param args command line
     */
    public static void main(final String[] args) {
        SpringApplication.run(CloudDiscoveryClientApplication.class, args);
    }
}
