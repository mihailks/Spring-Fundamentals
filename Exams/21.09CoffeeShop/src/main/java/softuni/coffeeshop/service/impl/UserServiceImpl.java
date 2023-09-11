package softuni.coffeeshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.coffeeshop.model.entity.User;
import softuni.coffeeshop.model.service.UserServiceModel;
import softuni.coffeeshop.repository.UserRepository;
import softuni.coffeeshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
//        userRepository.save(user);

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }
}
