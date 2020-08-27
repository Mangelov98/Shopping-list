package softuni.demoexam.service;

import softuni.demoexam.models.service.UserServiceModel;

public interface UserService {
    UserServiceModel findByUsername(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
