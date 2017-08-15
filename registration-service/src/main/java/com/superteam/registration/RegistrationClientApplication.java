package com.superteam.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class RegistrationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationClientApplication.class, args);
    }
}

