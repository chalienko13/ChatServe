package com.superteam.registration.service.impl;

import com.superteam.registration.dao.UserRepository;
import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.mapper.UserMapper;
import com.superteam.registration.model.User;
import com.superteam.registration.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User persist(UserRegisterDto userRegisterDto) {
        logger.debug("Start persist user");
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        User user = userMapper.mapRegisterUser(userRegisterDto);
        logger.debug("Map from dto to real user = ", user);

//        userRepository.save(user); // todo uncomment when will be work mongo repository
        logger.debug("Persisted user = ", user);

        return user;
    }










}
