package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Main entry point for clients.
 *
 * Created by johnny on 03/08/2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/service")
@Slf4j
public class ServiceController {

    private static final String PERSON_SERVICE = "PERSON-SERVICE";
    private static final String PERSON_NAME_PATH = "/person/name";
    private final RestTemplate restTemplate;

    private DiscoveryClient discoveryClient;
    private URI personPath;

    @Autowired
    public ServiceController(DiscoveryClient discoveryClient) throws URISyntaxException {
        this.discoveryClient = discoveryClient;
        personPath = new URI(PERSON_NAME_PATH);
        this.restTemplate = new RestTemplate();
    }

    /**
     * Pairs a random person with a random assignment and returns the results.
     *
     * @return the result
     */
    @RequestMapping("")
    public String getPairing() {
        String person = getPerson();

        String message = String.format("\"%s\" will be assigned to xxxx", person);
        log.info(message);

        return message;
    }

    private String getPerson() {
        List<ServiceInstance> list = discoveryClient.getInstances(PERSON_SERVICE);
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri != null) {
                URI full = uri.resolve(personPath);
                String name = restTemplate.getForObject(full, String.class);
                return name;
            }
        }

        log.error("No person returned");
        return null;
    }
}
