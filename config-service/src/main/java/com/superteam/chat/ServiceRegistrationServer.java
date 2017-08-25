package com.superteam.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistrationServer {
    public ServiceRegistrationServer() {
    }

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application");
        SpringApplication.run(ServiceRegistrationServer.class, args);


    }
}