package com.superteam.registration.service;

import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.model.User;

public interface UserService {

    User persist(UserRegisterDto userRegisterDto);
}
