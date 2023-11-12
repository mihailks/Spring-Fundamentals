package com.softuni.mobilelele.service.impl;

import com.softuni.mobilelele.model.entity.UserEntity;
import com.softuni.mobilelele.model.entity.UserRoleEntity;
import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import com.softuni.mobilelele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileleUserDetailServiceTest {

    MobileleUserDetailService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new MobileleUserDetailService(
                mockUserRepository
        );
    }

    @Test
    void testUserNotFoundShouldThrow() {

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("gosho@mail.bg"));
    }

    @Test
    void testMock() {

        UserEntity userEntity = new UserEntity().setFirstName("Misho");

        when(mockUserRepository.findByEmail("testme@abv.bg"))
                .thenReturn(Optional.of(userEntity));

        Optional<UserEntity> userEntityOptional = mockUserRepository.findByEmail("testme@abv.bg");

        UserEntity user = userEntityOptional.get();

        Assertions.assertEquals("Misho", user.getFirstName());
    }

    @Test
    void testUserFoundException() {
        //ARRANGE
        UserEntity userEntity = createTestUser();

        when(mockUserRepository.findByEmail(userEntity.getEmail()))
                .thenReturn(Optional.of(userEntity));

        //ACT
        UserDetails userDetails = serviceToTest.loadUserByUsername(userEntity.getEmail());

        //ASSERT
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(userEntity.getEmail(), userDetails.getUsername(), "Username is not mapped to email");
        Assertions.assertEquals(userEntity.getPassword(), userDetails.getPassword(), "Password is not the same");
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN), "The user is not admin");
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER), "The user is not user");


    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails.getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEntity createTestUser() {
        return new UserEntity().setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("test@softuni.bg")
                .setActive(false)
                .setPassword("123")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.USER),
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN)
                ));
    }


}
