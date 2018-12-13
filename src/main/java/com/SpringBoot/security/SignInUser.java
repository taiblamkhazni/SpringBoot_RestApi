package com.SpringBoot.security;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignInUser {
    @NotNull
    private String username;
    @NotNull
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignInUser() {
    }

    public SignInUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
