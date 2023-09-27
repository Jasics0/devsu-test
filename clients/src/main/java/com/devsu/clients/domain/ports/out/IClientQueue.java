package com.devsu.clients.domain.ports.out;

import com.devsu.clients.domain.model.Client;

public interface IClientQueue {
        void sendQueue(Client client);
}
