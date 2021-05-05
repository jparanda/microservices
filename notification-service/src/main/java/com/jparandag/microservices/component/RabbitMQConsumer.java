package com.jparandag.microservices.component;

import com.jparandag.microservices.config.RabbitMQConfig;
import com.jparandag.microservices.consumer.Consumer;
import com.jparandag.microservices.exception.NotificationException;
import com.jparandag.microservices.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    private final Consumer consumer;

    @Autowired
    public RabbitMQConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_REGISTER_NOTIFICATION_QUEUE)
    public void receiveMessage(final User user)  {
        log.info("Receive message from rabbitMQ {}", user);
        try {
            consumer.processMessage(user);
        } catch (NotificationException e) {
            log.error(e.getMessage());
        }
    }


}
