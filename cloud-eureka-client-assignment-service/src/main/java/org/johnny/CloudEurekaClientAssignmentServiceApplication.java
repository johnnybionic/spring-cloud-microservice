package org.johnny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudEurekaClientAssignmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaClientAssignmentServiceApplication.class, args);
	}
}
