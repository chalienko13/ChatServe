package com.superteam.registration.service;



import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@PropertySource("classpath:application.properties")
public class UserHelper {
    private static final String REGISTER_CONFIRM_ENDPOINT = "endpoint.register.confirm";

    @Resource
    private Environment env;

    public String getEndpoint(String id, String token){
        return String.format(env.getProperty(REGISTER_CONFIRM_ENDPOINT), id, token);
    }

}
