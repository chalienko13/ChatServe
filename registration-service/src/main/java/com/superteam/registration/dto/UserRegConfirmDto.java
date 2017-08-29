package com.superteam.registration.dto;

public class UserRegConfirmDto {

    private String username;
    private String activationLink;
    private String email;

    public UserRegConfirmDto() {
    }

    public UserRegConfirmDto(String username, String activationLink, String email) {
        this.username = username;
        this.activationLink = activationLink;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
