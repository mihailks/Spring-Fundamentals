package softuni.coffeeshop.service;

import softuni.coffeeshop.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
