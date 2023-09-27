package com.devsu.transactions.global.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    @Value("${devsu.queue.register}")
    private String registerQueue;

    @Value("${devsu.queue.deleted}")
    private String deleteQueue;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue registeredQueue() {
        return new Queue(registerQueue, true);
    }

    @Bean
    public Queue deletedQueue() {
        return new Queue(deleteQueue, true);
    }
}
