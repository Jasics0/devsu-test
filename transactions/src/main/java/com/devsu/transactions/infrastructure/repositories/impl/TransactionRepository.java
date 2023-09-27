package com.devsu.transactions.infrastructure.repositories.impl;

import com.devsu.transactions.domain.model.Transaction;
import com.devsu.transactions.domain.ports.out.ITransactionRepository;
import com.devsu.transactions.global.exceptions.DevsuException;
import com.devsu.transactions.infrastructure.entities.AccountEntity;
import com.devsu.transactions.infrastructure.entities.TransactionEntity;
import com.devsu.transactions.infrastructure.mappers.InfraTransactionMapper;
import com.devsu.transactions.infrastructure.repositories.jpa.JpaAccountRepository;
import com.devsu.transactions.infrastructure.repositories.jpa.JpaTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionRepository implements ITransactionRepository {

    private final JpaTransactionRepository jpaTransactionRepository;

    private final JpaAccountRepository jpaAccountRepository;

    @Override
    public Transaction save(Transaction transaction) {

        try {
            TransactionEntity transactionEntity = InfraTransactionMapper.toEntity(transaction);

            TransactionEntity transactionEntitySaved = jpaTransactionRepository.save(transactionEntity);

            return InfraTransactionMapper.toDomain(transactionEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_ALREADY_EXISTS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_CREATED);
        }
    }

    @Override
    public Transaction findById(Long idTransaction) {

        try {
            Optional<TransactionEntity> transactionEntity = jpaTransactionRepository.findById(idTransaction);

            if (transactionEntity.isEmpty()) {
                throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
            }

            TransactionEntity transactionEntityFound = transactionEntity.get();

            return InfraTransactionMapper.toDomain(transactionEntityFound);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }
    }

    @Override
    public List<Transaction> findByIdAccount(Long idAccount) {

        try {
            List<TransactionEntity> transactionEntity = jpaTransactionRepository.findByIdAccount(idAccount);

            if (transactionEntity.isEmpty()) {
                throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
            }

            return InfraTransactionMapper.toDomainList(transactionEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }
    }

    @Override
    public List<Transaction> findByIdPerson(String idPerson) {
        List<AccountEntity> accountEntity = jpaAccountRepository.findActiveAccountsByIdPerson(idPerson);

        if (accountEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        List<TransactionEntity> allTransactionsEntity = new ArrayList<>();

        for (AccountEntity account : accountEntity) {

            List<TransactionEntity> transactionEntity = jpaTransactionRepository.findByIdAccount(account.getId());

            allTransactionsEntity.addAll(transactionEntity);

        }

        if (allTransactionsEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }

        return InfraTransactionMapper.toDomainList(allTransactionsEntity);
    }

    @Override
    public List<Transaction> findByIdAccountAndRange(Long idAccount, LocalDate startDate, LocalDate endDate) {
        try {
            List<TransactionEntity> transactionEntity = jpaTransactionRepository.findByIdAccountAndDateBetween(idAccount, startDate, endDate);

            if (transactionEntity.isEmpty()) {
                log.error("Transactions not found");
            }

            return InfraTransactionMapper.toDomainList(transactionEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }
    }

    @Override
    public List<Transaction> findByIdPersonAndRange(String idPerson, LocalDate startDate, LocalDate endDate) {
        List<AccountEntity> accountEntity = jpaAccountRepository.findActiveAccountsByIdPerson(idPerson);

        if (accountEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        List<TransactionEntity> allTransactionsEntity = new ArrayList<>();

        for (AccountEntity account : accountEntity) {

            List<TransactionEntity> transactionEntity = jpaTransactionRepository.findByIdAccountAndDateBetween(account.getId(), startDate, endDate);

            allTransactionsEntity.addAll(transactionEntity);

        }

        if (allTransactionsEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }

        return InfraTransactionMapper.toDomainList(allTransactionsEntity);    }

    @Override
    public Transaction update(Transaction transaction, Long idTransaction) {

        Optional<TransactionEntity> transactionEntity = jpaTransactionRepository.findById(idTransaction);

        if (transactionEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }

        TransactionEntity transactionEntityFound = transactionEntity.get();

        try {
            if (transaction.getAmount() != null) transactionEntityFound.setAmount(transaction.getAmount());
            if (transaction.getDate() != null) transactionEntityFound.setDate(transaction.getDate());
            if (transaction.getIdAccount() != null) transactionEntityFound.setIdAccount(transaction.getIdAccount());

            return InfraTransactionMapper.toDomain(jpaTransactionRepository.save(transactionEntityFound));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_UPDATED);
        }
    }

    @Override
    public void delete(Long idTransaction) {

        Optional<TransactionEntity> transactionEntity = jpaTransactionRepository.findById(idTransaction);

        if (transactionEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_FOUND);
        }

        TransactionEntity transactionEntityFound = transactionEntity.get();

        try {
            jpaTransactionRepository.delete(transactionEntityFound);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_NOT_DELETED);
        }
    }
}
