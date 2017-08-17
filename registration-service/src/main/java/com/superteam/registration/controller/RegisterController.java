package com.superteam.registration.controller;


import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.model.User;
import com.superteam.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegisterController {


    private final UserService userService;

    @Autowired
    public RegisterController( UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String registerUser(UserRegisterDto userRegisterDto){
        User user = userService.persist(userRegisterDto);

        return user.toString();
    }
}
