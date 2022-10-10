package by.tretyak.application.service;


import by.tretyak.application.models.User;
import by.tretyak.application.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update (Long id, User updateUser){
        updateUser.setId(id);
        userRepository.save(updateUser);
    }

    public void save (User user){
        userRepository.save(user);
    }
}
