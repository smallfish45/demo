package com.example.controller;

import com.example.service.LeafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaf")
public class LeafServiceController {

    @Autowired
    private LeafService leafService;

    @GetMapping()
    public String getSnowflakeId() {
        return leafService.getSnowflakeId();
    }

}
