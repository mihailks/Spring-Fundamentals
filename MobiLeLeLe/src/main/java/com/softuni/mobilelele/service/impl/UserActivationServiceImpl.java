package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.event.UserRegisterEvent;
import com.softuni.mobilelele.service.EmailService;
import com.softuni.mobilelele.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener(UserRegisterEvent.class)
    @Override
    public void userRegister(UserRegisterEvent event) {
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUsername());
    }

    @Override
    public void cleanObsoleteActivationLinks() {
        throw new IllegalArgumentException("todo");
    }
}
