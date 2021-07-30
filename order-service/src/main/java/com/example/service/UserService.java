package com.example.service;

import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 20805693
 */
@FeignClient(name = "USER-SERVICE")
public interface UserService {

    /**
     * getUserByName
     * @param name
     * @return
     */
    @GetMapping("/user")
    User getUserByName(@RequestParam(value = "name") String name);

}
