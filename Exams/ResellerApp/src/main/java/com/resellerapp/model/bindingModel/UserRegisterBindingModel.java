package com.resellerapp.model.bindingModel;

import com.resellerapp.model.entity.validation.FieldMatch;
import com.resellerapp.model.entity.validation.UniqueUserEmail;
import com.resellerapp.model.entity.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegisterBindingModel {
    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    @UniqueUsername
    private String username;
    @NotNull(message = "Email cannot be empty notnull")
    @Pattern(regexp = "^.*@.*$", message = "Email must contain @")
//    @Email(message = "Email must contain @")
    @Size(min = 1, message = "Email cannot be empty!")
    @UniqueUserEmail
    private String email;
    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;
    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
