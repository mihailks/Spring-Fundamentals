package com.dictionaryapp.model.DTO;

import com.dictionaryapp.validation.FieldMatch;
import com.dictionaryapp.validation.UniqueUserEmail;
import com.dictionaryapp.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegisterDTO {
    @NotNull
    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    private String username;

    @NotNull(message = "Email cannot be empty.")
    @UniqueUserEmail
    @Pattern(regexp = "^.*@.*$", message = "Email must contain @")
    private String email;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters long.")
    private String password;

    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
