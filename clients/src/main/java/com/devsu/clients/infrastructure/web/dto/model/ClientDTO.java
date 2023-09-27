package com.devsu.clients.infrastructure.web.dto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientDTO extends PersonDTO {

    private String idClient;
    private String password;
    private boolean status;

    public ClientDTO() {
        super();
    }
}
