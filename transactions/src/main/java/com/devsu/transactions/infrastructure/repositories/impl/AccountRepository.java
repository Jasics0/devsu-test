package com.devsu.transactions.infrastructure.repositories.impl;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.ports.out.IAccountRepository;
import com.devsu.transactions.global.exceptions.DevsuException;
import com.devsu.transactions.infrastructure.entities.AccountEntity;
import com.devsu.transactions.infrastructure.entities.enums.AccountTypeEntity;
import com.devsu.transactions.infrastructure.mappers.InfraAccountMapper;
import com.devsu.transactions.infrastructure.repositories.jpa.JpaAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository implements IAccountRepository {

    private final JpaAccountRepository jpaAccountRepository;

    @Override
    public Account save(Account account) {

        try {
            AccountEntity accountEntity = InfraAccountMapper.toEntity(account);

            AccountEntity accountEntitySaved = jpaAccountRepository.save(accountEntity);

            return InfraAccountMapper.toDomain(accountEntitySaved);
        }catch (DataIntegrityViolationException e){
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_ALREADY_EXISTS);
        }
        catch (Exception e){
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_CREATED);
        }
    }

    @Override
    public Account findById(Long idAccount) {

        Optional<AccountEntity> accountEntity = jpaAccountRepository.findActiveAccountByIdAccount(idAccount);

        if (accountEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        AccountEntity accountEntityFound = accountEntity.get();
        return InfraAccountMapper.toDomain(accountEntityFound);

    }

    @Override
    public List<Account> findByIdPerson(String idPerson) {

        List<AccountEntity> listAccount = jpaAccountRepository.findActiveAccountsByIdPerson(idPerson);

        if (listAccount.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        return InfraAccountMapper.toDomainList(listAccount);

    }

    @Override
    public Account update(Account account, Long idAccount) {

        Optional<AccountEntity> accountEntity = jpaAccountRepository.findById(idAccount);

        if (accountEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        AccountEntity accountEntityFound = accountEntity.get();
        try {
            if (account.getAccountType() != null)
                accountEntityFound.setAccountTypeEntity(AccountTypeEntity.valueOf(account.getAccountType().name()));
            if (account.getBalance() != null) accountEntityFound.setBalance(account.getBalance());
            if (account.getIdPerson() != null) accountEntityFound.setIdPerson(account.getIdPerson());
            if (account.isStatus()!=accountEntityFound.isStatus()) accountEntityFound.setStatus(account.isStatus());

            return InfraAccountMapper.toDomain(jpaAccountRepository.save(accountEntityFound));
        } catch (Exception e) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_UPDATED);
        }

    }

    @Override
    public Account updateBalance(Account account) {

        Optional<AccountEntity> accountEntity = jpaAccountRepository.findActiveAccountByIdAccount(account.getId());

        if (accountEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        AccountEntity accountEntityFound = accountEntity.get();

        try {
            accountEntityFound.setBalance(account.getBalance());
            return InfraAccountMapper.toDomain(jpaAccountRepository.save(accountEntityFound));
        } catch (Exception e) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_UPDATED);
        }
    }

    @Override
    public void delete(Long idAccount) {
        Optional<AccountEntity> accountEntity = jpaAccountRepository.findById(idAccount);

        if (accountEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        AccountEntity accountEntityFound = accountEntity.get();

        accountEntityFound.setStatus(false);

        jpaAccountRepository.save(accountEntityFound);
    }
}
