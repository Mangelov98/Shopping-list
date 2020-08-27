package softuni.demoexam.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.demoexam.models.entity.User;
import softuni.demoexam.models.service.UserServiceModel;
import softuni.demoexam.repository.UserRepository;
import softuni.demoexam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel findByUsername(String username) {

        return this.userRepository.findByUsername(username).map(user -> this.modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }
}
