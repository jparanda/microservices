package com.jparandag.microservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparandag.microservices.config.RabbitMQConfig;
import com.jparandag.microservices.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserNotificationSender {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public UserNotificationSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendUserNotification(UserDTO userDTO) {
        try {
            String notificationJson = objectMapper.writeValueAsString(userDTO);
            Message message = MessageBuilder
                    .withBody(notificationJson.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            this.rabbitTemplate.convertAndSend(RabbitMQConfig.USER_REGISTER_NOTIFICATION_QUEUE, message);
            log.info("The notification was sent to RabbitMQ...!!");
        } catch (JsonProcessingException jex) {
            log.error("Error trying to parse the notification message {}", jex);
        }
    }
}
