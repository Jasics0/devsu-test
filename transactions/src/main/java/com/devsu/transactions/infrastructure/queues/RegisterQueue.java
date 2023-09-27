package com.devsu.transactions.infrastructure.queues;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.model.Client;
import com.devsu.transactions.domain.ports.out.IClientQueue;
import com.devsu.transactions.infrastructure.repositories.impl.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterQueue implements IClientQueue {

    private final AccountRepository accountRepository;

    @RabbitListener(queues = {"${devsu.queue.register}"})
    public void getLast(@Payload Client client) {

        log.info("Creando la cuenta de: {}", client.getName());

        Account account = new Account();
        account.setIdPerson(client.getIdPerson());

        accountRepository.save(account);
        makeSlow();

    }

    private void makeSlow() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
