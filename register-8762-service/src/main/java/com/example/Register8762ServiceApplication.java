package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Register8762ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Register8762ServiceApplication.class, args);
    }


}
