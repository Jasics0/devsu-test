package com.devsu.clients.infrastructure.repositories.jpa;

import com.devsu.clients.infrastructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaClientRepository extends CrudRepository<ClientEntity, String> {
    @Query("SELECT c FROM ClientEntity c WHERE c.personEntity.idPerson = ?1 AND c.status = true")
    Optional<ClientEntity> findClientActiveByPerson(String idPerson);
    ClientEntity findByIdClientAndStatus(String idClient, boolean status);
}
