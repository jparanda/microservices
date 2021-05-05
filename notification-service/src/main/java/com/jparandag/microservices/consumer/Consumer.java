package com.jparandag.microservices.consumer;

import com.jparandag.microservices.exception.NotificationException;
import com.jparandag.microservices.model.User;

public interface Consumer {

    void processMessage(User User) throws NotificationException;
}