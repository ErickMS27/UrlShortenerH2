package com.urlshortenerh2.service;

import com.urlshortenerh2.dto.UserDTO;
import com.urlshortenerh2.model.User;
import com.urlshortenerh2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(),
                savedUser.getPassword());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserDTO(user.get().getId(), user.get().getUsername(), user.get().getEmail(),
                    user.get().getPassword());
        }
        return null;
    }

}