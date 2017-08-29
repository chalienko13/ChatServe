package com.superteam.registration.repository;

import com.superteam.registration.model.real.UserToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends MongoRepository<UserToken, String> {
    public UserToken findByEmail(String email);
}
