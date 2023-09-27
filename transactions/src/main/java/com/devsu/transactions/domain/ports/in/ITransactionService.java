package com.devsu.transactions.domain.ports.in;

import com.devsu.transactions.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {

    Transaction save(Transaction transaction);

    Transaction findByIdTransaction(Long idTransaction);

    List<Transaction> findByAccount(Long idAccount);

    List<Transaction> findByAccountAndRange(Long idAccount, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByClientId(String idPerson);

    List<Transaction> findByClientIdAndRange(String idPerson, LocalDate startDate, LocalDate endDate);

    Transaction update(Transaction transaction, Long idTransaction);

    void delete(Long idTransaction);
}
