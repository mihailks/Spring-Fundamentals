package softuni.coffeeshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.coffeeshop.model.entity.User;
import softuni.coffeeshop.model.service.UserServiceModel;
import softuni.coffeeshop.model.view.UserViewModel;
import softuni.coffeeshop.repository.UserRepository;
import softuni.coffeeshop.service.UserService;
import softuni.coffeeshop.util.CurrentUser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
//        userRepository.save(user);

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUserNameAndPassword(String username, String password) {
        return userRepository
                .findFirstByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void logoutUser() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public List<UserViewModel> findAllUserAndCountOfOrdersOrderByOrdersDesc() {
        return userRepository
                .findAllByOrderByOrderDesc()
                .stream()
                .map(user -> {
                    UserViewModel userViewModel = new UserViewModel();

                    userViewModel.setUserName(user.getUsername());
                    userViewModel.setCountOfOrders(user.getOrders().size());

                    return userViewModel;
                })
                .collect(Collectors.toList());
    }
}
