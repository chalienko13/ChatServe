package com.superteam.registration.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.superteam.registration.dto.UserRegConfirmDto;
import com.superteam.registration.dto.UserRegisterDto;
import com.superteam.registration.dto.mapper.UserMapper;
import com.superteam.registration.model.User;
import com.superteam.registration.model.real.UserToken;
import com.superteam.registration.rabbitmq.EmailProducer;
import com.superteam.registration.repository.UserRepository;
import com.superteam.registration.repository.UserTokenRepository;
import com.superteam.registration.service.UserHelper;
import com.superteam.registration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final EmailProducer emailProducer;
    private final UserTokenRepository userTokenRepository;
    @Autowired
    private UserHelper userHelper;
    private ObjectMapper mapper = new ObjectMapper();

    @Value("${register}")
    private String queue;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, EmailProducer emailProducer, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.emailProducer = emailProducer;
        this.userTokenRepository = userTokenRepository;
    }


    public ResponseEntity<String> persist(UserRegisterDto userRegisterDto) throws JsonProcessingException {
        logger.debug("Start persist user");
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        User user = userMapper.mapRegisterUser(userRegisterDto);
        logger.debug("Map from dto to real user = {}", user);

        userRepository.save(user);
        logger.debug("Persisted user = {}", user);

        UserToken userToken = generateUserToken(user);
        logger.debug("User token = {}", userToken);

        String endpoint = userHelper.getEndpoint(user.getId(), userToken.getToken());
        logger.debug("Endpoint = {}", endpoint);


        UserRegConfirmDto userRegConfirmDto = userMapper.mapToUserRegConfirm(user, endpoint);
        logger.debug("User confirm DTO = {}", userRegConfirmDto);
        emailProducer.sendTo(queue, mapper.writeValueAsString(userRegConfirmDto));
        logger.debug("Email for user with id = {} is send", user.getId());

        return new ResponseEntity<>("You have been successful registered", HttpStatus.OK);
    }




    private UserToken generateUserToken(User user){
        UserToken userToken = new UserToken();

        userToken.setToken(UUID.randomUUID().toString());
        userToken.setEmail(user.getEmail());
        userToken.setActivated(false);

        userTokenRepository.save(userToken);
        logger.debug("User token for email = {} is created", user.getEmail());

        return userToken;
    }

    public ResponseEntity<String> checkConfirmationToken(String id, String token){
        User user = userRepository.findOne(id);
        if (user != null){
            UserToken userToken = userTokenRepository.findByEmail(user.getEmail());
            if (userToken != null){
                if (userToken.getActivated()){
                    return new ResponseEntity<>(String.format("User with email {} already activated", userToken.getEmail()), HttpStatus.OK);
                }else {
                    if (token.equals(userToken.getToken())) {
                        userToken.setActivated(true);
                        userTokenRepository.save(userToken);
                        return new ResponseEntity<>(String.format("User with email {} successful activated", user.getEmail()), HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<>(String.format("User with this credentials is not found"), HttpStatus.OK);
    }








}
