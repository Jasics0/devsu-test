package com.devsu.clients.infrastructure.web.mappers;

import com.devsu.clients.domain.model.Client;
import com.devsu.clients.infrastructure.web.dto.model.ClientDTO;

public class WebClientMapper {
    public WebClientMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Client toDomain(String idPerson, String password) {

        Client client = new Client();

        client.setIdPerson(idPerson);
        client.setPassword(password);

        return client;
    }

    public static Client toDomain(ClientDTO clientDTO) {

        Client client = new Client();

        client.setIdPerson(clientDTO.getIdPerson());
        client.setName(clientDTO.getName());
        client.setGender(clientDTO.getGender());
        client.setAge(clientDTO.getAge());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhone());
        client.setIdClient(clientDTO.getIdClient());
        client.setPassword(clientDTO.getPassword());
        client.setStatus(clientDTO.isStatus());

        return client;
    }

    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setIdPerson(client.getIdPerson());
        clientDTO.setName(client.getName());
        clientDTO.setGender(client.getGender());
        clientDTO.setAge(client.getAge());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setIdClient(client.getIdClient());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setStatus(client.isStatus());

        return clientDTO;
    }
}
