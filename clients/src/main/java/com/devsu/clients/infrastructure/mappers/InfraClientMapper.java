package com.devsu.clients.infrastructure.mappers;

import com.devsu.clients.domain.model.Client;
import com.devsu.clients.infrastructure.entities.ClientEntity;
import com.devsu.clients.infrastructure.entities.PersonEntity;

public class InfraClientMapper {
    public InfraClientMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ClientEntity toEntity(Client client) {

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPassword(client.getPassword());

        PersonEntity personEntity = new PersonEntity();
        personEntity.setIdPerson(client.getIdPerson());
        personEntity.setName(client.getName());
        personEntity.setGender(client.getGender());
        personEntity.setAge(client.getAge());
        personEntity.setAddress(client.getAddress());
        personEntity.setPhone(client.getPhone());

        clientEntity.setPersonEntity(personEntity);

        return clientEntity;
    }

    public static Client toDomain(ClientEntity clientEntity) {

        Client client = new Client();

        client.setIdClient(clientEntity.getIdClient());
        client.setPassword(clientEntity.getPassword());
        client.setStatus(clientEntity.isStatus());

        client.setIdPerson(clientEntity.getPersonEntity().getIdPerson());
        client.setName(clientEntity.getPersonEntity().getName());
        client.setGender(clientEntity.getPersonEntity().getGender());
        client.setAge(clientEntity.getPersonEntity().getAge());
        client.setAddress(clientEntity.getPersonEntity().getAddress());
        client.setPhone(clientEntity.getPersonEntity().getPhone());

        return client;
    }
}
