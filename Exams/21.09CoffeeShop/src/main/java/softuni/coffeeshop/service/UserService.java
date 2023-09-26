package softuni.coffeeshop.service;

import softuni.coffeeshop.model.entity.User;
import softuni.coffeeshop.model.service.UserServiceModel;
import softuni.coffeeshop.model.view.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUserNameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logoutUser();

    User findById(Long id);

    List<UserViewModel> findAllUserAndCountOfOrdersOrderByOrdersDesc();


}
