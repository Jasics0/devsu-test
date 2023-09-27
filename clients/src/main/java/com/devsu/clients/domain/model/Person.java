package com.devsu.clients.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String idPerson;
    private String name;
    private char gender;
    private int age;
    private String address;
    private String phone;
}
