package com.thee5176.record.springboot_cqrs_query.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thee5176.record.springboot_cqrs_query.model.Transactions;
import com.thee5176.record.springboot_cqrs_query.repository.TransactionRepository;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping
    public List<Transactions> getTransaction() {
        return transactionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestBody Transactions transaction) {
        transactionRepository.saveAndFlush(transaction);
        
        return ResponseEntity.ok("Transaction created succesfully");
    }

    @PutMapping
    public ResponseEntity<String> putMethodName(@RequestParam UUID uuid, @RequestBody Transactions transaction) {
        transaction.setId(uuid);
        transactionRepository.save(transaction);
        
        return ResponseEntity.ok("Transaction updated succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTransaction(@RequestParam UUID uuid) {
        transactionRepository.deleteById(uuid);

        return ResponseEntity.ok("Transaction deleted succesfully");
    }
}
