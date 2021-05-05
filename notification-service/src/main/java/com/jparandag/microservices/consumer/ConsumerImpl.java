package com.jparandag.microservices.consumer;

import com.jparandag.microservices.exception.NotificationException;
import com.jparandag.microservices.model.User;
import com.jparandag.microservices.service.EmailServiceImpl;
import com.jparandag.microservices.service.MessageService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class ConsumerImpl implements Consumer {

    private final JavaMailSender javaMailSender;

    @Autowired
    public ConsumerImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void processMessage(User user) throws NotificationException {
        MessageService messageService = getMessageService(user);
        messageService.sendMessage(user);
    }

    private MessageService getMessageService(User user) throws NotificationException {
        switch (user.getNotification()) {
            case "EMAIL":
                return new EmailServiceImpl(javaMailSender, user);
            default:
                throw new NotificationException("Notification " + user.getNotification() + " not supported!");
        }
    }
}
