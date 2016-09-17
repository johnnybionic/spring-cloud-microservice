package org.johnny.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Main entry point for clients.
 * <p>
 * Created by johnny on 03/08/2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/person")
@Slf4j
public class PairingController extends AbstractPairingController {

    private DiscoveryClient discoveryClient;

    @Autowired
    public PairingController(final DiscoveryClient discoveryClient) throws URISyntaxException {
        super();
        this.discoveryClient = discoveryClient;

    }

    /**
     * Implementation of the method that uses {@link DiscoveryClient}.
     *
     * @param service the service required
     * @param subpath subpath of the service (the main URL is found by discovery)
     * @return the result
     */
    protected String service(final String service, final URI subpath) {
        final List<ServiceInstance> list = discoveryClient.getInstances(service);
        if (list != null && list.size() > 0) {
            final URI uri = list.get(0).getUri();
            if (uri != null) {
                final URI full = uri.resolve(subpath);
                return getRestTemplate().getForObject(full, String.class);
            }
        }

        log.error("Nothing returned for: " + service);
        return null;

    }
}
