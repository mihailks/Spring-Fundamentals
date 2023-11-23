package com.softuni.mobilelele.testUtils;

import com.softuni.mobilelele.model.entity.UserRoleEntity;
import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import com.softuni.mobilelele.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class dbInit implements CommandLineRunner {
    private UserRoleRepository userRoleRepository;
    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    new UserRoleEntity().setRole(UserRoleEnum.USER),
                    new UserRoleEntity().setRole(UserRoleEnum.ADMIN)
            ));
        }
    }
}
