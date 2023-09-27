package com.devsu.clients.application.services;

import com.devsu.clients.domain.model.Client;
import com.devsu.clients.domain.ports.in.IClientService;
import com.devsu.clients.domain.ports.out.IClientQueue;
import com.devsu.clients.domain.ports.out.IClientRepository;
import com.devsu.clients.global.exceptions.DevsuException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository clientRepository;

    @Qualifier("registerQueue")
    private final IClientQueue registerQueue;

    @Qualifier("deleteQueue")
    private final IClientQueue deleteQueue;

    @Override
    public Client save(Client client) {

        if (client.getIdPerson()==null  || client.getIdPerson().isBlank()) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The person id is required");
        }

        if (client.getName()==null  || client.getName().isBlank()) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The name is required");
        }

        if (client.getPassword()==null  || client.getPassword().isBlank()) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The password is required");
        }

        Client clientSaved = clientRepository.save(client);

        registerQueue.sendQueue(clientSaved);

        return clientSaved;
    }

    @Override
    public Client findByPersonId(String idPerson) {
        if (idPerson==null || idPerson.isBlank()) throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The person id is required");
        return clientRepository.findByPersonId(idPerson);
    }

    @Override
    public Client findByIdClient(String idClient) {
        if (idClient==null || idClient.isBlank()) throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The client id is required");
        return clientRepository.findByIdClient(idClient);
    }

    @Override
    public Client update(Client client, String idPerson) {
        if (idPerson==null || idPerson.isBlank()) throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The person id is required");
        return clientRepository.update(client, idPerson);
    }

    @Override
    public void delete(String idPerson) {
        if (idPerson==null || idPerson.isBlank()) throw new DevsuException(DevsuException.DevsuError.CLIENT_DATA_REQUIRED, "The person id is required");
        Client clientDeleted = clientRepository.delete(idPerson);
        deleteQueue.sendQueue(clientDeleted);
    }
}
