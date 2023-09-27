package com.devsu.clients.application.service;

import com.devsu.clients.application.services.ClientService;
import com.devsu.clients.domain.model.Client;
import com.devsu.clients.infrastructure.queues.DeleteQueue;
import com.devsu.clients.infrastructure.queues.RegisterQueue;
import com.devsu.clients.infrastructure.repositories.implementation.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientTestRepository;

    @Mock
    private RegisterQueue testQueue;

    @Mock
    private DeleteQueue testQueue2;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientService(clientTestRepository, testQueue, testQueue2);
    }


    @Test
    public void saveTest() {

        when(clientTestRepository.save(any(Client.class))).thenReturn(getClientData());

        Client client = clientService.save(getClientData());

        assert client != null;
    }

    @Test
    public void findByPersonIdTest() {

        when(clientTestRepository.findByPersonId(any(String.class))).thenReturn(getClientData());

        Client client = clientService.findByPersonId("12345");

        assert client != null;
    }

    @Test
    public void findByIdClientTest() {

        when(clientTestRepository.findByIdClient(any(String.class))).thenReturn(getClientData());

        Client client = clientService.findByIdClient("12345");

        assert client != null;
    }


    static Client getClientData() {
        Client client = new Client();
        client.setIdClient("12345");
        client.setName("John Doe");
        client.setGender('M');
        client.setAge(30);
        client.setAddress("123 Main St");
        client.setPhone("1234567890");
        client.setPassword("securePassword");
        client.setStatus(true);
        client.setIdPerson("12345");
        return client;
    }

}
