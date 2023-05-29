package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity
                .status(200)
                .body(service.getAllCustomers());
    }

    @GetMapping("/customers/{name}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {
        return ResponseEntity
                .status(200)
                .body(service.getCustomerByName(name));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> addOrUpdateCustomer(@RequestBody CustomerDTO toSave) {
        return ResponseEntity
                .status(200)
                .body(service.addOrUpdateCustomer(toSave));
    }
}
