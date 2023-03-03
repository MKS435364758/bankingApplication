package com.bank.webAppliaction.service;

import com.bank.webAppliaction.exception.ContentNoFound;
import com.bank.webAppliaction.model.Customer;
import com.bank.webAppliaction.model.Transaction;
import com.bank.webAppliaction.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){
        try {
            return customerRepository.save(customer);
        }catch (RuntimeException ex){
            throw new ContentNoFound(ex.getMessage());

        }
    }

    public Customer updateCustomer(Customer customer){
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        if( ! customer1.isPresent()) throw new ContentNoFound("No account found with ID :\t"+customer.getId()+"\tand Name :\t"+customer.getName());
        customer1.get().setModifiedOn(Timestamp.from(Instant.now()));
        try {
            return customerRepository.save(customer1.get());
        }catch (RuntimeException ex){
            throw new ContentNoFound("Bad request!!!");
        }
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id){
        try {
            customerRepository.deleteAllById(Collections.singleton(id));
        } catch (RuntimeException ex){
            throw new ContentNoFound("Bad request!!!");
        }
    }

    public List<Transaction> getTransaction(Long id){

        return customerRepository.findById(id).get().getTransactions();
    }

}
