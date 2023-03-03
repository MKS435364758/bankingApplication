package com.bank.webAppliaction.controller;

import com.bank.webAppliaction.model.Transaction;
import com.bank.webAppliaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    TransactionService transactionService;

    @PostMapping("/add/{id}/{amount}")
    public ResponseEntity<Transaction> saveTransaction(@PathVariable("id") Long customerId, @PathVariable("amount")BigDecimal amount){
        return new ResponseEntity<>( transactionService.customerTransaction(customerId,amount),
                HttpStatus.OK
        );
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(){
        return new ResponseEntity<>( transactionService.getTransactions(),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable("id") Long id){
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

}
