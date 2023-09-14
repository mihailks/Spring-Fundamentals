package softuni.coffeeshop.service;

import softuni.coffeeshop.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);


    UserServiceModel findByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logoutUser();
}
