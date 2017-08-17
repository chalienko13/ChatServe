package com.superteam.registration.model;

public interface User {

    void setId(String id);
    String getId();

    void setFirstName(String firstName);
    String getFirstName();

    void setLastName(String lastName);
    String getLastName();

    void setEmail(String email);
    String getEmail();

    void setPhoneNumber(String phoneNumber);
    String getPhoneNumber();

    void setPassword(String password);
    String getPassword();
}
