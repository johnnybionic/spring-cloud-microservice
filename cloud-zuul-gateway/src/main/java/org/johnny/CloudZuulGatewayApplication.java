package org.johnny;

import org.johnny.filters.pre.SimpleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class CloudZuulGatewayApplication {

    /**
     * Entry point.
     *
     * @param args command line
     */
    public static void main(final String[] args) {
        SpringApplication.run(CloudZuulGatewayApplication.class, args);
    }

    /**
     * Configuration - returns a Filter that will be placed in the chain by Spring.
     *
     * @return a Filter
     */
    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }

}
