package softuni.battleships.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.battleships.model.bindingModel.UserRegisterDTO;
import softuni.battleships.model.entity.UserEntity;
import softuni.battleships.repository.UserRepository;
import softuni.battleships.service.UserService;
@Service
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;
private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        userRepository.save(modelMapper.map(userRegisterDTO, UserEntity.class));
    }
}
