package com.superteam.notification;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NotificationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationClientApplication.class, args);
    }
}
