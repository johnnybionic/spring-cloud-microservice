package org.johnny.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign client for Person service.
 *
 * Remember to add
 * @EnableFeignClients ands @EnableEurekaClient
 *
 * Created by johnny on 04/08/2016.
 */

@FeignClient("PERSON-SERVICE")
public interface PersonClient {

    @RequestMapping("/person/name")
    String getName();

    /*
    * This is for Hystrix - it is intended to fail, so that the fallback is invoked
     */
    @RequestMapping("/person/badCall")
    String getNameThatDoesntWork();

}

