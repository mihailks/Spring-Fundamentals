package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.dto.UserLoginDTO;
import com.softuni.mobilelele.model.dto.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    boolean userLogin(UserLoginDTO userLoginDTO);

    void logoutUser();
}
