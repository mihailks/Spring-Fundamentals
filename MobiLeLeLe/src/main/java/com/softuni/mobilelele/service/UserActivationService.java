package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.event.UserRegisterEvent;


public interface UserActivationService {
    void userRegister(UserRegisterEvent event);

    void cleanObsoleteActivationLinks();
    String createActivationCode(String userEmail);
}
