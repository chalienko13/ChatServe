package com.superteam.registration.dto.mapper;

import com.superteam.registration.dto.UserRegConfirmDto;
import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.model.User;
import com.superteam.registration.model.real.UserReal;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapRegisterUser(UserRegisterDto registerDto){
        UserReal user =  new UserReal();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        return user;
    }


    public UserRegConfirmDto mapToUserRegConfirm(User user, String activationLink){
        UserRegConfirmDto confirmDto = new UserRegConfirmDto();
        confirmDto.setUsername(String.format("%s %s", user.getFirstName(), user.getLastName()));
        confirmDto.setEmail(user.getEmail());
        confirmDto.setActivationLink(activationLink);
        return confirmDto;
    }
}
