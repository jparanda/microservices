package com.jparandag.microservices.service;

import com.jparandag.microservices.converter.UserConverter;
import com.jparandag.microservices.dto.UserDTO;
import com.jparandag.microservices.exception.UserNotFoundException;
import com.jparandag.microservices.model.User;
import com.jparandag.microservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNotificationSender userNotificationSender;

    @Override
    public User registerUser(UserDTO userDTO) {
      log.info("User in the service {}", userDTO);

      //I need register on the db
      UserConverter userConverter = new UserConverter();
      User user = userRepository.save(userConverter.fromDto(userDTO));

      //I need send the notification to RabbitMQ
      userNotificationSender.sendUserNotification(userDTO);

      //if all is ok return HTTP 200 ok

      //if something fail we need to do rollback the DB transaction

      return user;
    }

    @Override
    public List<UserDTO> retrieveAllUsers() {
        List<User> users = userRepository.findAll();
        UserConverter userConverter = new UserConverter();
        return userConverter.fromEntity(users);
    }

    @Override
    public UserDTO retrieveUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(email));

        UserConverter userConverter = new UserConverter();
        return userConverter.fromEntity(user);
    }
}
