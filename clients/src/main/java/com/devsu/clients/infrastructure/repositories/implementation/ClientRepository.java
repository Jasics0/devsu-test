package com.devsu.clients.infrastructure.repositories.implementation;

import com.devsu.clients.domain.model.Client;
import com.devsu.clients.domain.ports.out.IClientRepository;
import com.devsu.clients.global.exceptions.DevsuException;
import com.devsu.clients.infrastructure.entities.ClientEntity;
import com.devsu.clients.infrastructure.entities.PersonEntity;
import com.devsu.clients.infrastructure.mappers.InfraClientMapper;
import com.devsu.clients.infrastructure.repositories.jpa.JpaClientRepository;
import com.devsu.clients.infrastructure.repositories.jpa.JpaPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository implements IClientRepository {

    private final JpaClientRepository jpaClientRepository;

    private final JpaPersonRepository jpaPersonRepository;

    @Override
    public Client save(Client client) {
        try {
            ClientEntity clientEntity = InfraClientMapper.toEntity(client);

            jpaPersonRepository.save(clientEntity.getPersonEntity());

            ClientEntity clientEntitySaved = jpaClientRepository.save(clientEntity);

            return InfraClientMapper.toDomain(clientEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_ALREADY_EXISTS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.CLIENT_NOT_CREATED);
        }
    }

    @Override
    public Client findByPersonId(String idPerson) {


        Optional<ClientEntity> clientEntity = jpaClientRepository.findClientActiveByPerson(idPerson);

        if (clientEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_NOT_FOUND);
        }

        ClientEntity clientEntityFound = clientEntity.get();

        return InfraClientMapper.toDomain(clientEntityFound);
    }

    @Override
    public Client findByIdClient(String idClient) {

        ClientEntity clientEntity = jpaClientRepository.findByIdClientAndStatus(idClient, true);

        return InfraClientMapper.toDomain(clientEntity);
    }

    @Override
    public Client update(Client client, String idPerson) {

        Optional<ClientEntity> clientEntity = jpaClientRepository.findClientActiveByPerson(idPerson);

        if (clientEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_NOT_FOUND);
        }

        try {
            ClientEntity clientEntityFound = clientEntity.get();


            if (client.getPassword() != null) clientEntityFound.setPassword(client.getPassword());
            if (client.isStatus()) clientEntityFound.setStatus(true);

            if (client.getIdPerson() != null) clientEntityFound.getPersonEntity().setIdPerson(client.getIdPerson());
            if (client.getName() != null) clientEntityFound.getPersonEntity().setName(client.getName());
            if (client.getGender() != '\u0000') clientEntityFound.getPersonEntity().setGender(client.getGender());
            if (client.getAge() != 0) clientEntityFound.getPersonEntity().setAge(client.getAge());
            if (client.getAddress() != null) clientEntityFound.getPersonEntity().setAddress(client.getAddress());
            if (client.getPhone() != null) clientEntityFound.getPersonEntity().setPhone(client.getPhone());

            return InfraClientMapper.toDomain(jpaClientRepository.save(clientEntityFound));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.CLIENT_NOT_UPDATED);
        }
    }

    @Override
    public Client delete(String idPerson) {


        Optional<ClientEntity> clientEntity = jpaClientRepository.findClientActiveByPerson(idPerson);

        if (clientEntity.isEmpty()) {
            throw new DevsuException(DevsuException.DevsuError.CLIENT_NOT_FOUND);
        }

        try {
            ClientEntity clientEntityFound = clientEntity.get();

            clientEntityFound.setStatus(false);

            jpaClientRepository.save(clientEntityFound);

            return InfraClientMapper.toDomain(clientEntityFound);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DevsuException(DevsuException.DevsuError.CLIENT_NOT_DELETED);
        }
    }
}
