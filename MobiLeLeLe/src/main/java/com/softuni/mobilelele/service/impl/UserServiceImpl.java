package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.dto.UserRegistrationDTO;
import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.model.event.UserRegisterEvent;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {

        userRepository.save(map(userRegistrationDTO));

        applicationEventPublisher.publishEvent(new UserRegisterEvent(
                "UserService", userRegistrationDTO.email(), userRegistrationDTO.firstName() + " " + userRegistrationDTO.lastName()
        ));
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        return new UserEntity()
                .setActive(false)
                .setFirstName(userRegistrationDTO.firstName())
                .setLastName(userRegistrationDTO.lastName())
                .setEmail(userRegistrationDTO.email())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
    }
}
