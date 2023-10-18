package softuni.pathfinder.service;

import softuni.pathfinder.model.entity.User;
import softuni.pathfinder.model.serviceModel.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logOutUser();

    UserServiceModel findById(Long id);

    boolean isUsernameTaken(String username);

    User findCurrentLoginUserEntity();
}
