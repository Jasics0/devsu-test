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

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteQueue implements IClientQueue {

    private final AccountRepository accountRepository;

    @RabbitListener(queues = {"${devsu.queue.deleted}"})
    public void getLast(@Payload Client client) {

        log.info("Eliminando la cuenta de: {}", client.getName());

        List<Account> accounts = accountRepository.findByIdPerson(client.getIdPerson());

        accounts.forEach(account -> {
            accountRepository.delete(account.getId());
        });

        makeSlow();

    }

    private void makeSlow() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
