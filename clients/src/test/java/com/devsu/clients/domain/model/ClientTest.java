package com.devsu.clients.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {
    @Test
    void clientPropertiesTest() {
        Client client = new Client();
        client.setIdClient("12345");
        client.setName("John Doe");
        client.setGender('M');
        client.setAge(30);
        client.setAddress("123 Main St");
        client.setPhone("1234567890");
        client.setPassword("securePassword");
        client.setStatus(true);

        assertEquals("12345", client.getIdClient());
        assertEquals("John Doe", client.getName());
        assertEquals('M', client.getGender());
        assertEquals(30, client.getAge());
        assertEquals("123 Main St", client.getAddress());
        assertEquals("1234567890", client.getPhone());
        assertEquals("securePassword", client.getPassword());
        assertTrue(client.isStatus());
    }

}
