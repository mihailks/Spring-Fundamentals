package com.softuni.mobilelele.testUtils;

import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;

    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(UserRoleEnum.ADMIN));
    }

    private UserEntity createUser(String email, List<UserRoleEnum> roles) {
        var rolesFromDb = userRoleRepository.findAllByRoleIn(roles);

        UserEntity userEntity = new UserEntity()
                .setEmail(email)
                .setPassword("test")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setActive(true)
                .setRoles(rolesFromDb);
        return userRepository.save(userEntity);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }


}
