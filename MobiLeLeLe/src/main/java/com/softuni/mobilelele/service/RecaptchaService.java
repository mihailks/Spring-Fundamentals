package com.softuni.mobilelele.service;

import com.softuni.mobilelele.model.dto.RecaptchaResponseDTO;

import java.util.Optional;

public interface RecaptchaService {
    Optional<RecaptchaResponseDTO> verify(String token);
}
