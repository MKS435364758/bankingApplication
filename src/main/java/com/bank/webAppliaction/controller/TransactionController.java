package com.bank.webAppliaction.controller;

import com.bank.webAppliaction.model.Transaction;
import com.bank.webAppliaction.model.TransactionDetails;
import com.bank.webAppliaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    TransactionService transactionService;

    @PostMapping("/debit")
    public ResponseEntity<Transaction> amountDebitTransaction(@RequestBody TransactionDetails details){ // subtract amount
        return new ResponseEntity<>( transactionService.debit(details),
                HttpStatus.OK
        );
    }

    @PostMapping("/credit")
    public ResponseEntity<Transaction> amountCreditTransaction(@RequestBody TransactionDetails details){ // add amount
        return new ResponseEntity<>( transactionService.credit(details),
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
