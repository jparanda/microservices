package com.jparandag.microservices.service;

import com.jparandag.microservices.dto.UserDTO;
import com.jparandag.microservices.model.User;

import java.util.List;

public interface UserRegistrationService {

     User registerUser(UserDTO userDTO);

     List<UserDTO> retrieveAllUsers();

     UserDTO retrieveUserByEmail(String email);
}
