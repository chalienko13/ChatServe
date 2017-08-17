package com.superteam.registration.dao;

import com.superteam.registration.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);
    public User findByPhoneNumber(String phoneNumber);
    public List<User> findByLastName(String lastName);
    public List<User> findByFirstName(String firstName);

}
