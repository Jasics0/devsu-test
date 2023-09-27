package com.devsu.clients.infrastructure.web.controllers;

import com.devsu.clients.domain.ports.in.IClientService;
import com.devsu.clients.infrastructure.web.dto.model.ClientDTO;
import com.devsu.clients.infrastructure.web.dto.responses.ResponseDTO;
import com.devsu.clients.infrastructure.web.mappers.WebClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    public ResponseEntity<ResponseDTO> saveClient(@RequestBody ClientDTO clientDTO) {

        ClientDTO client = WebClientMapper.toDTO(clientService.save(WebClientMapper.toDomain(clientDTO)));
        return new ResponseEntity<>(new ResponseDTO<>(client, "Client saved successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/{idPerson}")
    public ResponseEntity<ResponseDTO> getClient(@PathVariable String idPerson) {

        ClientDTO client = WebClientMapper.toDTO(clientService.findByPersonId(idPerson));
        return new ResponseEntity<>(new ResponseDTO<>(client, "Client found successfully"), HttpStatus.OK);
    }

    @PutMapping("/{idPerson}")
    public ResponseEntity<ResponseDTO> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable String idPerson) {

        ClientDTO client = WebClientMapper.toDTO(clientService.update(WebClientMapper.toDomain(clientDTO), idPerson));
        return new ResponseEntity<>(new ResponseDTO<>(client, "Client updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{idPerson}")
    public ResponseEntity<ResponseDTO> deleteClient(@PathVariable String idPerson) {

        clientService.delete(idPerson);
        return new ResponseEntity<>(new ResponseDTO<>("Client deleted successfully"), HttpStatus.OK);
    }

}
