package com.devsu.transactions.domain.ports.out;


import com.devsu.transactions.domain.model.Client;

public interface IClientQueue {
    void getLast(Client client);
}
