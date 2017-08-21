package com.superteam.registration.controller;


import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.model.User;
import com.superteam.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/registration")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

//    http://localhost:8085/registration/user?firstName=doe&lastName=json&email=admin@gmail.com&phoneNumber=1234566789&password=123123  test line

    @RequestMapping(value = "/user", method = RequestMethod.GET) // todo change from RequestMethod.GET to RequestMethod.POST
    public ResponseEntity<String> registerUser(UserRegisterDto userRegisterDto){
        return userService.persist(userRegisterDto);
    }
}
