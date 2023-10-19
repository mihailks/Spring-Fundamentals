package com.resellerapp.service;

import com.resellerapp.model.bindingModel.UserRegisterBindingModel;
import com.resellerapp.model.serviceModel.UserServiceModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String password);

    void logout();
}
