package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.entity.UserActivationLinkEntity;
import com.softuni.mobilelele.model.event.UserRegisterEvent;
import com.softuni.mobilelele.repository.UserActivationLinkRepository;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.EmailService;
import com.softuni.mobilelele.service.UserActivationService;
import com.softuni.mobilelele.service.exeption.ObjectNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;

    private final UserActivationLinkRepository userActivationLinkRepository;

    public UserActivationServiceImpl(EmailService emailService, UserRepository userRepository, UserActivationLinkRepository userActivationLinkRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationLinkRepository = userActivationLinkRepository;
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void userRegister(UserRegisterEvent event) {
        String activationCode = createActivationCode(event.getUserEmail());
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUsername(), activationCode);
    }

    @Override
    public void cleanObsoleteActivationLinks() {
//        throw new IllegalArgumentException("todo");
    }

    @Override
    public String createActivationCode(String userEmail) {
        UserActivationLinkEntity userActivationLinkEntity = new UserActivationLinkEntity();
        String activationCode = generateActivationCode();
        userActivationLinkEntity.setActivationCode(activationCode);
        userActivationLinkEntity.setCreated(Instant.now());
        userActivationLinkEntity.setUserEntity(userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException("User with email " + userEmail + " not found!")));

        userActivationLinkRepository.save(userActivationLinkEntity);

        return activationCode;
    }

    private static String generateActivationCode() {
        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randIndex = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randIndex));
        }
        return activationCode.toString();
    }
}
