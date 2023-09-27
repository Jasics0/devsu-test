package com.devsu.transactions.infrastructure.web.mappers;

import com.devsu.transactions.domain.model.Transaction;
import com.devsu.transactions.infrastructure.web.dto.model.TransactionDTO;
import com.devsu.transactions.infrastructure.web.dto.model.TransactionSimpleDTO;

import java.util.List;

public class WebTransactionMapper {
    private WebTransactionMapper() {
    }

    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setIdAccount(transaction.getIdAccount());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setBalance(transaction.getBalance());
        return transactionDTO;
    }

    public static TransactionSimpleDTO toSimpleDTO(Transaction transaction) {
        TransactionSimpleDTO transactionDTO = new TransactionSimpleDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setIdAccount(transaction.getIdAccount());
        transactionDTO.setAmount(transaction.getAmount());
        return transactionDTO;
    }

    public static Transaction toModel(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setDate(transactionDTO.getDate());
        transaction.setIdAccount(transactionDTO.getIdAccount());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setBalance(transactionDTO.getBalance());
        return transaction;
    }
    public static List<TransactionSimpleDTO> toDTOList(List<Transaction> transactionList) {
        return transactionList.stream().map(WebTransactionMapper::toSimpleDTO).toList();
    }

}