package com.devsu.clients.domain.ports.in;

import com.devsu.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface IClientService {
    Client save(Client client);

    Client findByPersonId(String idPerson);

    Client findByIdClient(String idClient);

    Client update(Client client, String idPerson);

    void delete(String idPerson);
}
