package com.devsu.clients.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class ClientEntity{

    @Id
    private String idClient;

    private String password;

    private boolean status;

    @OneToOne
    @JoinColumn(name = "idPerson")
    private PersonEntity personEntity;

    public ClientEntity() {
        this.idClient = UUID.randomUUID().toString().replace("-", "");
        this.status = true;
    }
}
