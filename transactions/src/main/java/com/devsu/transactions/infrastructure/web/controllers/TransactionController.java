package com.devsu.transactions.infrastructure.web.controllers;

import com.devsu.transactions.domain.ports.in.ITransactionService;
import com.devsu.transactions.infrastructure.web.dto.model.TransactionDTO;
import com.devsu.transactions.infrastructure.web.dto.model.TransactionSimpleDTO;
import com.devsu.transactions.infrastructure.web.dto.responses.ResponseDTO;
import com.devsu.transactions.infrastructure.web.mappers.WebTransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody TransactionDTO transactionDTO) {

        TransactionDTO transaction = WebTransactionMapper.toDTO(transactionService.save(WebTransactionMapper.toModel(transactionDTO)));

        return new ResponseEntity<>(new ResponseDTO<>(transaction, "Transaction saved successfully"), org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("/{idTransaction}")
    public ResponseEntity<ResponseDTO> findByIdTransaction(@PathVariable Long idTransaction) {

        TransactionDTO transaction = WebTransactionMapper.toDTO(transactionService.findByIdTransaction(idTransaction));

        return new ResponseEntity<>(new ResponseDTO<>(transaction, "Transaction found successfully"), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/account/{idAccount}")
    public ResponseEntity<ResponseDTO> findByIdAccount(@PathVariable Long idAccount) {

        List<TransactionSimpleDTO> transaction = WebTransactionMapper.toDTOList(transactionService.findByAccount(idAccount));

        return new ResponseEntity<>(new ResponseDTO<>(transaction, "Transactions found successfully"), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/client/{idPerson}")
    public ResponseEntity<ResponseDTO> findByIdPerson(@PathVariable String idPerson) {

        List<TransactionSimpleDTO> transaction = WebTransactionMapper.toDTOList(transactionService.findByClientId(idPerson));

        return new ResponseEntity<>(new ResponseDTO<>(transaction, "Transactions found successfully"), org.springframework.http.HttpStatus.OK);
    }

    @PutMapping("/{idTransaction}")
    public ResponseEntity<ResponseDTO> update(@RequestBody TransactionDTO transactionDTO, @PathVariable Long idTransaction) {

        TransactionSimpleDTO transaction = WebTransactionMapper.toSimpleDTO(transactionService.update(WebTransactionMapper.toModel(transactionDTO), idTransaction));

        return new ResponseEntity<>(new ResponseDTO<>(transaction, "Transaction updated successfully"), org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping("/{idTransaction}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long idTransaction) {

        transactionService.delete(idTransaction);

        return new ResponseEntity<>(new ResponseDTO<>("Transaction deleted successfully"), org.springframework.http.HttpStatus.OK);
    }
}
