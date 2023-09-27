package com.devsu.clients.infrastructure.web.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String idPerson;
    private String name;
    private char gender;
    private int age;
    private String address;
    private String phone;
}
