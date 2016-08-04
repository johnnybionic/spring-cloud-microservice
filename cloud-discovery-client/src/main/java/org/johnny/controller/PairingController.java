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
@RequestMapping("/person")
@Slf4j
public class PairingController extends AbstractPairingController {

    private DiscoveryClient discoveryClient;

    @Autowired
    public PairingController(DiscoveryClient discoveryClient) throws URISyntaxException {
        super();
        this.discoveryClient = discoveryClient;

    }

    protected String service(String service, URI subpath) {
        List<ServiceInstance> list = discoveryClient.getInstances(service);
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri != null) {
                URI full = uri.resolve(subpath);
                return restTemplate.getForObject(full, String.class);
            }
        }

        log.error("Nothing returned for: " + service);
        return null;

    }
}
