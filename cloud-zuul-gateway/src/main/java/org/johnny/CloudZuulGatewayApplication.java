package org.johnny;

import org.johnny.filters.pre.SimpleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class CloudZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudZuulGatewayApplication.class, args);
	}

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }

}
