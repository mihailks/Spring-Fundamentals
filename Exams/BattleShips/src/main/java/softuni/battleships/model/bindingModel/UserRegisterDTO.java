package softuni.battleships.model.bindingModel;

import softuni.battleships.model.entity.validation.FieldMatch;
import softuni.battleships.model.entity.validation.UniqueUserEmail;
import softuni.battleships.model.entity.validation.UniqueUsername;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegisterDTO {
    @NotNull
    @UniqueUsername
    @Size(min = 3, max = 10, message = "Username must be between 3 and 20 characters long.")
    private String username;
    @NotNull
    @Size(min = 5, max = 20, message = "FullName must be between 3 and 20 characters long.")
    private String fullName;
    @NotNull
    @Email(message = "Enter valid email address.")
    @UniqueUserEmail
    private String email;
    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long.")
    private String password;
    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long.")
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

    public String getFullName() {
        return fullName;
    }

    public UserRegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
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
