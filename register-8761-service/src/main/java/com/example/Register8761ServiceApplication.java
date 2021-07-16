package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Register8761ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Register8761ServiceApplication.class, args);
    }

}
