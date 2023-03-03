package com.bank.webAppliaction.controller;


import com.bank.webAppliaction.model.Customer;
import com.bank.webAppliaction.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>( customerService.saveCustomer(customer),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>( customerService.updateCustomer(customer),
                HttpStatus.OK
        );
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomer(){
        return new ResponseEntity<>( customerService.getCustomers(),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransactionHistory(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getTransaction(id),
                HttpStatus.OK
        );
    }


}
