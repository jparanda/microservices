package com.jparandag.microservices.converter;

import com.jparandag.microservices.dto.UserDTO;
import com.jparandag.microservices.model.User;

public class UserConverter extends AbstractConverter<User, UserDTO> {

    @Override
    public User fromDto(UserDTO dto) {
        User user = new User();
        user.setId(null);
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUser());
        user.setNotification(dto.getNotification());
        return user;
    }

    @Override
    public UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUser(user.getUserName());
        userDTO.setNotification(user.getNotification());
        return userDTO;
    }
}
