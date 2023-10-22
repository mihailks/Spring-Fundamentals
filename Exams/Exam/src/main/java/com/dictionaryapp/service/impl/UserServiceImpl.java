package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.DTO.UserRegisterDTO;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        userRepository.save(modelMapper.map(userRegisterDTO, UserEntity.class));
    }

    @Override
    public CurrentUser findByUsernameAndPassword(String username, String password) {
        return userRepository.findFirstByUsernameAndPassword(username,password)
                .map(userEntity -> modelMapper.map(userEntity, CurrentUser.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id)
                .setUsername(username);
    }

    @Override
    public void logoutUser() {
        currentUser.setId(null)
                .setUsername(null);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
