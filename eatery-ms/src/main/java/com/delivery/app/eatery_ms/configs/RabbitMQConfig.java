package com.delivery.app.eatery_ms.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.eatery.name}")
    private String eateryQueueName;

    @Bean
    public Queue eateryQueue() {
        return new Queue(eateryQueueName, true);
    }

    @Value("${broker.queue.code.validated.name}")
    private String codeQueueName;

    @Bean
    public Queue codeQueue() {
        return new Queue(codeQueueName, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
