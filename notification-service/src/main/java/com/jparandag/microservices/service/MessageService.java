package com.jparandag.microservices.service;

import com.jparandag.microservices.model.User;

public interface MessageService {

    void sendMessage(User user);
}
