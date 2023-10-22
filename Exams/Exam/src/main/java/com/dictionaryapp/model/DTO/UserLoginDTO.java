package com.dictionaryapp.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotNull
    @Size(min = 3, max = 10, message = "Username must be between 3 and 20 characters long.")
    private String username;
    @NotNull
    @Size(min = 3, max = 20, message = "FullName must be between 3 and 20 characters long.")
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}
