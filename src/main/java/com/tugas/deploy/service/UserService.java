package com.tugas.deploy.service;

import com.tugas.deploy.model.User;
import com.tugas.deploy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveDefaultUser() {
        if (!userRepository.existsById("admin")) {
            User user = new User("admin", "20230140154");
            userRepository.save(user);
        }
    }

    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }

        return false;
    }
}