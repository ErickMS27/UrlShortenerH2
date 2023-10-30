package com.urlshortenerh2.service;

import com.urlshortenerh2.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
}