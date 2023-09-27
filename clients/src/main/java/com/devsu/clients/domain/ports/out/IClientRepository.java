package com.devsu.clients.domain.ports.out;

import com.devsu.clients.domain.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository {
    Client save(Client client);

    Client findByPersonId(String idPerson);

    Client findByIdClient(String idClient);

    Client update(Client client, String idPerson);

    Client delete(String idPerson);
}
