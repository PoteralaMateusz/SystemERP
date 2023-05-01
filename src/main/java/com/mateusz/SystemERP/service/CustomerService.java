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
        List<Customer> customerList = repository.getAllCustomers();
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
        if (repository.getCustomerByName(name).isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(repository.getCustomerByName(name).get());

    }

    public ResponseEntity<?> addCustomer(String name) {
        if (name.isBlank()) {
            return ResponseEntity
                    .status(400)
                    .build();
        }
        repository.addCustomer(new Customer(name));
        return ResponseEntity
                .status(201)
                .build();
    }


}
