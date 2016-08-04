package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This version of the controller uses a load-balanced discovery client.
 *
 * Created by johnny on 04/08/2016.
 */
@RestController
@RequestMapping("/person/lb")
@Slf4j
public class LoadBalancedPersonController extends AbstractPairingController {

    private final LoadBalancerClient loadBalancer;

    @Autowired
    public LoadBalancedPersonController(LoadBalancerClient loadBalancer) throws URISyntaxException {
        super();

        this.loadBalancer = loadBalancer;
    }

    protected String service(String service, URI path) {
        ServiceInstance serviceInstance = loadBalancer.choose(service);
        URI uri = serviceInstance.getUri();
        URI resolved = uri.resolve(path);
        return restTemplate.getForObject(resolved, String.class);
    }

}
