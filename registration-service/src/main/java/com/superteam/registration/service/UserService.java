package com.superteam.registration.service;

import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> persist(UserRegisterDto userRegisterDto);
}
