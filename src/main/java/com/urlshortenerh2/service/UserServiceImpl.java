package com.urlshortenerh2.service;

import com.urlshortenerh2.dto.UserDTO;
import com.urlshortenerh2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }
}
