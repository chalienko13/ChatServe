package com.superteam.registration.model.real;

public class UserToken {
    private String id;
    private String email;
    private String token;
    private Boolean activated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", activated=" + activated +
                '}';
    }
}
