package com.jparandag.microservices.controller;

import com.jparandag.microservices.dto.UserDTO;
import com.jparandag.microservices.model.User;
import com.jparandag.microservices.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/registration/v1")
public class UserRegistrationResource {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDto) throws URISyntaxException {
        log.info("The userDTO object is {}", userDto);
        User userRegister = userRegistrationService.registerUser(userDto);
        return ResponseEntity.created(new URI(userRegister.getId().toString())).body(userRegister);
    }

    @GetMapping("/users")
    public List<UserDTO> retrieveAllUsers() {
        return userRegistrationService.retrieveAllUsers();
    }

    @GetMapping("/users/{email}")
    public UserDTO retrieveUser(@PathVariable String email) {
        return userRegistrationService.retrieveUserByEmail(email);
    }
}
