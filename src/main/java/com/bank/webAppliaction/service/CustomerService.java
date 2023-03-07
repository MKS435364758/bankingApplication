package com.bank.webAppliaction.service;

import com.bank.webAppliaction.exception.ContentNoFound;
import com.bank.webAppliaction.model.Customer;
import com.bank.webAppliaction.model.CustomerDetails;
import com.bank.webAppliaction.model.Transaction;
import com.bank.webAppliaction.model.TransactionDetails;
import com.bank.webAppliaction.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {

    CustomerRepository customerRepository;

    TransactionService transactionService;

    public Customer saveCustomer(Customer customer){
        try {
            return customerRepository.save(customer);
        }catch (RuntimeException ex){
            throw new ContentNoFound(ex.getMessage());

        }
    }

    public Customer updateCustomerName(CustomerDetails details){
        try {
            Optional<Customer> customer = customerRepository.findById(details.getId());
            customer.get().setName(details.getName());
            return customerRepository.save(customer.get());
        } catch (RuntimeException ex){
            throw new ContentNoFound(ex.getMessage());
        }
    }

    public Customer creditCustomerBalance(CustomerDetails customerDetails){
        TransactionDetails transactionDetails = new TransactionDetails(customerDetails.getId(),
                customerDetails.getBalance(), CustomerDetails.TYPE);
        transactionDetails.setDescription("Amount "+customerDetails.getBalance()+" debited to your account on : "+
                Date.from(Instant.now()));
        transactionService.credit(transactionDetails);
        return customerRepository.findById(customerDetails.getId()).get();
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id){
//        try {
            customerRepository.deleteAllById(Collections.singleton(id));
//        } catch (RuntimeException ex){
//            throw new ContentNoFound("Bad request!!!\n"+ex.getMessage());
//        }
    }

    public List<Transaction> getTransaction(Long id){

        return customerRepository.findById(id).get().getTransactions();
    }


    public Customer updateCustomer(Customer customer){
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        if( ! customerRepository.findById(customer.getId()).isPresent()) throw new ContentNoFound("No account found with ID :\t"+customer.getId()+"\tand Name :\t"+customer.getName());
        customer1.get().setModifiedOn(Timestamp.from(Instant.now()));
        customer1.get().setName(customer.getName());
        customer1.get().setBalance(customer.getBalance());
        try {
            return customerRepository.save(customer1.get());
        }catch (RuntimeException ex){
            throw new ContentNoFound("Bad request!!!\n"+ex.getMessage());
        }
    }

}
