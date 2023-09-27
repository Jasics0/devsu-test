package com.devsu.transactions.domain.ports.out;

import com.devsu.transactions.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository {
    Transaction save(Transaction transaction);

    Transaction findById(Long idTransaction);

    List<Transaction> findByIdAccount(Long idAccount);

    List<Transaction> findByIdPerson(String idPerson);

    List<Transaction> findByIdAccountAndRange(Long idAccount, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByIdPersonAndRange(String idPerson, LocalDate startDate, LocalDate endDate);

    Transaction update(Transaction transaction,Long idTransaction);

    void delete(Long idTransaction);
}
