package com.devsu.transactions.infrastructure.mappers;

import com.devsu.transactions.domain.model.Transaction;
import com.devsu.transactions.infrastructure.entities.TransactionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InfraTransactionMapper {

    private InfraTransactionMapper() {
    }

    public static TransactionEntity toEntity(Transaction transaction) {

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setId(transaction.getId());
        transactionEntity.setIdAccount(transaction.getIdAccount());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setDate(transaction.getDate());

        return transactionEntity;
    }

    public static Transaction toDomain(TransactionEntity transactionEntity) {

        Transaction transaction = new Transaction();

        transaction.setId(transactionEntity.getId());
        transaction.setIdAccount(transactionEntity.getIdAccount());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setDate(transactionEntity.getDate());

        return transaction;
    }

    public static List<Transaction> toDomainList(List<TransactionEntity> transactionEntity) {
        return transactionEntity.stream().map(InfraTransactionMapper::toDomain).collect(Collectors.toList());
    }
}
