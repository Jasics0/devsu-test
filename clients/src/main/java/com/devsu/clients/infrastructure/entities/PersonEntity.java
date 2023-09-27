package com.devsu.clients.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "people")
@Getter
@Setter
public class PersonEntity {
    @Id
    private String idPerson;
    private String name;
    private char gender;
    private int age;
    private String address;
    private String phone;
}
