package softuni.battleships.service;

import softuni.battleships.model.bindingModel.UserRegisterDTO;
import softuni.battleships.model.entity.UserEntity;
import softuni.battleships.util.CurrentUser;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    CurrentUser findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findById(Long id);

    void logoutUser();

}
