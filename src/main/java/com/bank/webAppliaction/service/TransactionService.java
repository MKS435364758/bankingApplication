package com.bank.webAppliaction.service;

import com.bank.webAppliaction.exception.ContentNoFound;
import com.bank.webAppliaction.model.Customer;
import com.bank.webAppliaction.model.Transaction;
import com.bank.webAppliaction.model.TransactionDetails;
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

    public Transaction debit(TransactionDetails details){ // subtract amount
        try {
            Optional<Customer> customer = customerRepository.findById(details.getId());
            if (!customer.isPresent()) throw new ContentNoFound("No account found with ID :\t" + details.getId());
            BigDecimal balance = customer.get().getBalance();
            if (balance.compareTo(details.getAmount()) == -1)
                throw new ContentNoFound("Not enough balance available\nCurrentBalance\t:\t" + balance);
            BigDecimal remainingBalance = balance.subtract(details.getAmount());
            return transactionMethod(customer.get(), balance, details);
        }catch (RuntimeException ex){
            throw new ContentNoFound(ex.getMessage());
        }
    }

    public Transaction credit(TransactionDetails details){ // add amount
        try {
            Optional<Customer> customer = customerRepository.findById(details.getId());
            if (!customer.isPresent()) throw new ContentNoFound("No account found with ID :\t" + details.getId());
            BigDecimal remainingBalance = customer.get().getBalance().add(details.getAmount());
            return transactionMethod(customer.get(), remainingBalance, details);
        }catch (RuntimeException ex){
            throw new ContentNoFound(ex.getMessage());
        }
    }


    private Transaction transactionMethod(Customer customer,BigDecimal balance,TransactionDetails details){
        try {
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setTransactionAmount(details.getAmount());
        transaction.setRemainBalance(balance);

        customerRepository.findById(details.getId()).get().setBalance(balance);

        transaction.setMade_on(Timestamp.from(Instant.now()));
        transaction.setTransactionType(details.getType());
        transaction.setDescription(details.getDescription() == null ? "No description available"  : details.getDescription());
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
