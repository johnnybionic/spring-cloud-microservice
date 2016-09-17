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
 * <p>
 * Created by johnny on 04/08/2016.
 */
@RestController
@RequestMapping("/person/lb")
@Slf4j
public class LoadBalancedPersonController extends AbstractPairingController {

    private final LoadBalancerClient loadBalancer;

    @Autowired
    public LoadBalancedPersonController(final LoadBalancerClient loadBalancer) throws URISyntaxException {
        super();

        this.loadBalancer = loadBalancer;
    }

    /**
     * Implementation of the service method that uses {@link LoadBalancerClient}.
     *
     * @param service the service required
     * @param path    the path
     * @return the result
     */
    protected String service(final String service, final URI path) {
        final ServiceInstance serviceInstance = loadBalancer.choose(service);
        final URI uri = serviceInstance.getUri();
        final URI resolved = uri.resolve(path);
        return getRestTemplate().getForObject(resolved, String.class);
    }

}
