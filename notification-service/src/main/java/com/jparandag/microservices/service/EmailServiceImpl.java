package com.jparandag.microservices.service;

import com.jparandag.microservices.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class EmailServiceImpl implements MessageService {

    private JavaMailSender javaMailSender;
    private User user;

    @Override
    public void sendMessage(User user) {
        log.info("Sending message through email " + user.getEmail());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("User Register Notification");
        msg.setText("Your user with mail " + user.getEmail() + " was register successfully in Skenda Services");

        try {
            javaMailSender.send(msg);
            log.info("Message sent");
        } catch (Exception ex) {
            log.error("Error sending the email notification {}", ex);
        }
    }
}
