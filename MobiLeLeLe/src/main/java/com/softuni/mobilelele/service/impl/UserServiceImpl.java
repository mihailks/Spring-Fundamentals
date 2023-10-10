package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.UserLoginDTO;
import com.softuni.mobilelele.model.dto.UserRegistrationDTO;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.UserService;
import com.softuni.mobilelele.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }


    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        // FIXME DOEST`N MAP
        UserEntity user = modelMapper.map(userRegistrationDTO, UserEntity.class);
        System.out.println(user);
        userRepository.save(map(userRegistrationDTO));
//        userRepository.save(user);
    }

    @Override
    public boolean userLogin(UserLoginDTO userLoginDTO) {

        var userEntity = userRepository.findByEmail(userLoginDTO.email()).orElse(null);
        boolean loginSuccess = false;

        if (userEntity != null) {

            String rawPassword = userLoginDTO.password();
            String encodedPassword = userEntity.getPassword();

            loginSuccess = encodedPassword != null && passwordEncoder.matches(rawPassword, encodedPassword);

            if (loginSuccess){
                currentUser.setLogged(true)
                        .setFirstName(userEntity.getFirstName())
                        .setLastName(userEntity.getLastName());
            } else {
                currentUser.logout();
            }

        }
        return loginSuccess;

    }

    @Override
    public void logoutUser() {
        currentUser.logout();
    }


    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        return new UserEntity()
                .setActive(true)
                .setFirstName(userRegistrationDTO.firstName())
                .setLastName(userRegistrationDTO.lastName())
                .setEmail(userRegistrationDTO.email())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
    }
}
