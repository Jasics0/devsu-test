package com.devsu.clients.infrastructure.repositories.jpa;

import com.devsu.clients.infrastructure.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaPersonRepository extends CrudRepository<PersonEntity, String> {
}
