package com.devsu.clients.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Client extends Person{

    private String idClient;

    private String password;

    private boolean status;

    public Client() {
        super();
    }
}
