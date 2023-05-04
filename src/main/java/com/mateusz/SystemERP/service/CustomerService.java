package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.entity.Customer;
import com.mateusz.SystemERP.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = repository.findAll();
        if (customerList.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(customerList);
    }

    public ResponseEntity<Customer> getCustomerByName(String name) {
        if (repository.findCustomerByName(name).isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(repository.findCustomerByName(name).get());

    }

    public ResponseEntity<?> addCustomer(String name) {
        if (name.isBlank()) {
            return ResponseEntity
                    .status(400)
                    .build();
        }
        if(repository.findCustomerByName(name).isPresent()){
            return ResponseEntity
                    .status(409)
                    .build();
        }
        repository.save(new Customer(name));
        return ResponseEntity
                .status(201)
                .build();
    }


}
