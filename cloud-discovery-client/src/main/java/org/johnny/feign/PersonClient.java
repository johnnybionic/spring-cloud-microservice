package org.johnny.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign client for Person service.
 * <p>
 * Remember to add
 * EnableFeignClients ands EnableEurekaClient
 * <p>
 * Created by johnny on 04/08/2016.
 */

@FeignClient("PERSON-SERVICE")
public interface PersonClient {

    /**
     * Return the name of a person, e.g just a random name, or the next available etc.
     *
     * @return the name
     */
    @RequestMapping("/person/name")
    String getName();

    /**
     * This is for Hystrix - it is intended to fail, so that the fallback is invoked.
     *
     * @return should never return
     */
    @RequestMapping("/person/badCall")
    String getNameThatDoesntWork();

}

