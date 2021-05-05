package com.jparandag.microservices.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_REGISTER_NOTIFICATION_QUEUE = "notification-queue";
    public static final String USER_REGISTER_NOTIFICATION_EXCHANGE = "notification-exchange";

    @Bean
    public Queue notificationQueue() {
        return QueueBuilder.durable(USER_REGISTER_NOTIFICATION_QUEUE).build();
    }

    @Bean
    public Exchange notificationExchange() {
        return ExchangeBuilder.topicExchange(USER_REGISTER_NOTIFICATION_EXCHANGE).build();
    }

    @Bean
    public Binding binding(Queue notificationQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(notificationQueue).to(notificationExchange).with(USER_REGISTER_NOTIFICATION_QUEUE);
    }
}
