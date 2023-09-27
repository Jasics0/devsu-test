package com.devsu.clients.infrastructure.queues;

import com.devsu.clients.domain.model.Client;
import com.devsu.clients.domain.ports.out.IClientQueue;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;


@Component
@EnableRabbit
@RequiredArgsConstructor
public class RegisterQueue implements IClientQueue {

    @Value("${devsu.queue.register}")
    private String queueName;

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;
    private Queue queue;

    @PostConstruct
    public void setupRabbitTemplate() {
        rabbitTemplate.setMessageConverter(messageConverter);
        queue = new Queue(queueName, true);
    }

    public void sendQueue(Client client) {
        rabbitTemplate.convertAndSend(this.queue.getName(), client);
    }


}
