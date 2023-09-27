package com.devsu.transactions.infrastructure.entities;

import com.devsu.transactions.infrastructure.entities.enums.AccountTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity {

    @Id
    private Long id;
    @Column(name = "account_type")
    private AccountTypeEntity accountTypeEntity;
    private Double balance;
    private boolean status;
    @Column(name = "id_person")
    private String idPerson;
}
