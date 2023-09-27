package com.devsu.transactions.infrastructure.repositories.jpa;

import com.devsu.transactions.infrastructure.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaTransactionRepository extends CrudRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByIdAccount(Long idAccount);

    List<TransactionEntity> findByIdAccountAndDateBetween(Long idAccount, LocalDate startDate, LocalDate endDate);
}
