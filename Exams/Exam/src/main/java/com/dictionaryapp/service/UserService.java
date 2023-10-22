package com.dictionaryapp.service;

import com.dictionaryapp.model.DTO.UserRegisterDTO;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.util.CurrentUser;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    CurrentUser findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logoutUser();

    UserEntity findById(Long id);
}
