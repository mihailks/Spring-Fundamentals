package com.resellerapp.service.impl;

import com.resellerapp.model.bindingModel.UserRegisterBindingModel;
import com.resellerapp.model.entity.UserEntity;
import com.resellerapp.model.serviceModel.UserServiceModel;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        UserEntity user = modelMapper.map(userRegisterBindingModel, UserEntity.class);
        userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUserNameAndPassword(String username, String password) {
        return userRepository.findFirstByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id)
                .setUsername(username);
    }

    @Override
    public void logout() {
        currentUser.setUsername(null)
                .setId(null);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);

    }
}
