package com.mateusz.SystemERP.customer;

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
        return ResponseEntity
                .status(200)
                .body(service.getAllCustomers());
    }

    @GetMapping("/customers/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        return ResponseEntity
                .status(200)
                .body(service.getCustomerByName(name));
    }

    @PostMapping("/customers/{name}")
    public ResponseEntity<?> addCustomer(@PathVariable String name) {
        return ResponseEntity
                .status(200)
                .body(service.addCustomer(name));
    }
}
