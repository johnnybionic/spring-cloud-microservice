package org.johnny.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign client for Assignment service.
 *
 * Created by johnny on 04/08/2016.
 */
//@FeignClient(name="ASSIGNMENT-SERVICE", url="http://localhost:8200/assignments")
@FeignClient("ASSIGNMENT-SERVICE")
public interface AssignmentClient {

    @RequestMapping("/assignments")
    String getAssignment();
}
