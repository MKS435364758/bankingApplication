package com.bank.webAppliaction.service;

import com.bank.webAppliaction.exception.ContentNoFound;
import com.bank.webAppliaction.model.Customer;
import com.bank.webAppliaction.model.Transaction;
import com.bank.webAppliaction.repository.CustomerRepository;
import com.bank.webAppliaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    TransactionRepository transactionRepository;
    CustomerRepository customerRepository;

    public Transaction customerTransaction(Long customerId,BigDecimal amount){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if( ! customer.isPresent()) throw new ContentNoFound("No account found with ID :\t"+customerId);
        BigDecimal balance = customer.get().getBalance();
        if( balance.compareTo(amount)==-1) throw new ContentNoFound("Not enough balance available\nCurrentBalance\t:\t"+balance);
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer.get());
        transaction.setTransactionAmount(amount);
        transaction.setRemainBalance(balance.subtract(amount));
        customerRepository.findById(customerId).get().setBalance(transaction.getRemainBalance());
        transaction.setMade_on(Timestamp.from(Instant.now()));
        try {
            return transactionRepository.save(transaction);
        }catch (RuntimeException ex){
            throw new ContentNoFound(ex.getMessage());
        }
    }


    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Long id){
        try {
            transactionRepository.deleteAllById(Collections.singleton(id));
        } catch (RuntimeException ex){
            throw new ContentNoFound("Bad request!!!");
        }
    }



}