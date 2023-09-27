package com.devsu.transactions.infrastructure.web.controllers;

import com.devsu.transactions.domain.ports.in.IAccountService;
import com.devsu.transactions.infrastructure.web.dto.model.AccountDTO;
import com.devsu.transactions.infrastructure.web.dto.responses.ResponseDTO;
import com.devsu.transactions.infrastructure.web.mappers.WebAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody AccountDTO accountDTO) {

        AccountDTO account = WebAccountMapper.toDTO(accountService.save(WebAccountMapper.toDomain(accountDTO)));

        return new ResponseEntity<>(new ResponseDTO(account, "Account saved successfully"), org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("/{idAccount}")
    public ResponseEntity<ResponseDTO> findByIdAccount(@PathVariable Long idAccount) {

        AccountDTO account = WebAccountMapper.toDTO(accountService.findByIdAccount(idAccount));

        return new ResponseEntity<>(new ResponseDTO(account, "Account found successfully"), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/client/{idPerson}")
    public ResponseEntity<ResponseDTO> findByClientId(@PathVariable String idPerson) {

        List<AccountDTO> listAccounts = WebAccountMapper.toDTOList(accountService.findByClientId(idPerson));

        return new ResponseEntity<>(new ResponseDTO(listAccounts, "Account found successfully"), org.springframework.http.HttpStatus.OK);
    }

    @PutMapping("/{idAccount}")
    public ResponseEntity<ResponseDTO> update(@RequestBody AccountDTO accountDTO, @PathVariable Long idAccount) {

        AccountDTO account = WebAccountMapper.toDTO(accountService.update(WebAccountMapper.toDomain(accountDTO), idAccount));

        return new ResponseEntity<>(new ResponseDTO(account, "Account updated successfully"), org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping("/{idAccount}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long idAccount) {

        accountService.delete(idAccount);

        return new ResponseEntity<>(new ResponseDTO<>("Account deleted successfully"), org.springframework.http.HttpStatus.OK);
    }


}
