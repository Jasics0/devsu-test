package com.devsu.clients.application.service;

import com.devsu.clients.AccountDTO;
import com.devsu.clients.domain.model.Client;
import com.devsu.clients.domain.ports.in.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class IntegrationTest {

    @Autowired
    private IClientService clientService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void saveTest() {

        Client client = clientService.save(getClientData());

        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {};

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/accounts/client/"+client.getIdPerson(),
                HttpMethod.GET,
                null,
                typeRef
        );

        Map<String, Object> result = responseEntity.getBody();

        List<AccountDTO> accounts = (List<AccountDTO>) result.get("data");

        if (accounts!=null) {
            log.info("Accounts: {}", accounts);
            assertTrue(!accounts.isEmpty());
        } else {
            assertTrue(false);
        }


    }

    static Client getClientData() {
        Client client = new Client();
        client.setIdClient("0001");
        client.setName("John Doe");
        client.setGender('M');
        client.setAge(30);
        client.setAddress("123 Main St");
        client.setPhone("1234567890");
        client.setPassword("securePassword");
        client.setStatus(true);
        client.setIdPerson("0003");
        return client;
    }


}
