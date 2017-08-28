package com.superteam.registration.service.impl;

import com.google.gson.GsonBuilder;
import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.dto.mapper.UserMapper;
import com.superteam.registration.model.User;
import com.superteam.registration.rabbitmq.EmailProducer;
import com.superteam.registration.repository.UserRepository;
import com.superteam.registration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final EmailProducer emailProducer;


    @Value("${myqueue}")
    private String queue;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, EmailProducer emailProducer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.emailProducer = emailProducer;
    }


    public ResponseEntity<String> persist(UserRegisterDto userRegisterDto) {
        logger.debug("Start persist user");
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        User user = userMapper.mapRegisterUser(userRegisterDto);
        logger.debug("Map from dto to real user = {}", user);

        userRepository.save(user);
        logger.debug("Persisted user = {}", user);

        GsonBuilder writer = new GsonBuilder();

        emailProducer.sendTo(queue,writer.create().toJson(user));
        logger.debug("Email for user = {} is send", user);

        return new ResponseEntity<>("You have been successful registered", HttpStatus.OK);
    }










}
