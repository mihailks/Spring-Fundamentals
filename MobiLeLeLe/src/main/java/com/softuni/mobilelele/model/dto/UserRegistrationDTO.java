package com.softuni.mobilelele.model.dto;

import com.softuni.mobilelele.model.validation.FieldMatch;
import com.softuni.mobilelele.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "passwords are different."
)
public record UserRegistrationDTO(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,

        @UniqueUserEmail
        String email,
        @NotNull
        String password,
        @NotNull
        String confirmPassword) {

public static UserRegistrationDTO createEmpty(){
                return new UserRegistrationDTO(
                        null, null, null, null, null);
        }

}
