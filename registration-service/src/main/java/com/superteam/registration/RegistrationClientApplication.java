package com.superteam.registration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableEurekaClient
//@EnableGlobalMethodSecurity
@SpringBootApplication
public class RegistrationClientApplication {

    @Value("${register}")
    private String queueName;

    public static void main(String[] args) {
        SpringApplication.run(RegistrationClientApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }
}

