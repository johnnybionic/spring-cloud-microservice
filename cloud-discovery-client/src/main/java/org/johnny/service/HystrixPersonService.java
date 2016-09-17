package org.johnny.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.johnny.feign.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * A version of the service that uses Hystrix. The main call is intended to fail, so
 * that the fallback is invoked.
 * <p>
 * Created by johnny on 05/08/2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
@Slf4j
public class HystrixPersonService implements PersonService {

    private static final String DEFAULT_NAME = "unknown person";

    private final PersonClient personClient;

    @Autowired
    public HystrixPersonService(final PersonClient personClient) {
        this.personClient = personClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultName", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "100")
    })

    public String getName() {
        log.info("Invoking PersonClient method");
        return personClient.getNameThatDoesntWork();
    }

    /**
     * Default method that is invoked when getName() fails.
     *
     * @return the default name
     */
    private String getDefaultName() {
        log.info("Default method invoked");
        return DEFAULT_NAME;
    }
}
