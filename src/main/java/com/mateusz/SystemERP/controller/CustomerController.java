package com.mateusz.SystemERP.controller;

import com.mateusz.SystemERP.entity.Customer;
import com.mateusz.SystemERP.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/customers/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        return service.getCustomerByName(name);
    }

    @PostMapping("/customers/{name}")
    public ResponseEntity<?> addCustomer(@PathVariable String name) {
        return service.addCustomer(name);
    }
}
