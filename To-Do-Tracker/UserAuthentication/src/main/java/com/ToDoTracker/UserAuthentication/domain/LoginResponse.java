package com.ToDoTracker.UserAuthentication.domain;

import java.util.Map;

public class LoginResponse {
    private User user;
    private Map<String, String> secretKeyToken;

    public LoginResponse() {
    }

    public LoginResponse(User user, Map<String, String> secretKeyToken) {
        this.user = user;
        this.secretKeyToken = secretKeyToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, String> getSecretKeyToken() {
        return secretKeyToken;
    }

    public void setSecretKeyToken(Map<String, String> secretKeyToken) {
        this.secretKeyToken = secretKeyToken;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", secretKeyToken=" + secretKeyToken +
                '}';
    }
}
