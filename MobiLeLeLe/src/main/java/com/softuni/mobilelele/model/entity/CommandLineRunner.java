package com.softuni.mobilelele.model.entity;

import com.softuni.mobilelele.repository.BrandRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final BrandRepository brandRepository;
    private final PasswordEncoder passwordEncoder;

    public CommandLineRunner(BrandRepository brandRepository, PasswordEncoder passwordEncoder) {
        this.brandRepository = brandRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {


    }
}
