package com.devsu.transactions.infrastructure.repositories.jpa;

import com.devsu.transactions.infrastructure.entities.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAccountRepository extends CrudRepository<AccountEntity, Long> {
        @Query("SELECT a FROM AccountEntity a WHERE a.idPerson = ?1 AND a.status = true")
        List<AccountEntity> findActiveAccountsByIdPerson(String idPerson);

        List<AccountEntity> findByIdPersonAndStatus(String idPerson, boolean status);

        @Query("SELECT a FROM AccountEntity a WHERE a.id = ?1 AND a.status = true")
        Optional<AccountEntity> findActiveAccountByIdAccount(Long idAccount);
}
