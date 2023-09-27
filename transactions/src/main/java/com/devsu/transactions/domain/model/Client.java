package com.devsu.transactions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client{

    private String idClient;

    private String idPerson;

    private String name;

    private boolean status;
}
