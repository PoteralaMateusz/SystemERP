package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.dto.CustomerAddDTO;
import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity
                .status(200)
                .body(customerService.getAllCustomers());
    }

    @GetMapping("/customers/{name}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {
        return ResponseEntity
                .status(200)
                .body(customerService.getCustomerByName(name));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerAddDTO toSave) {
        return ResponseEntity
                .status(200)
                .body(customerService.addCustomer(toSave));
    }

    @PatchMapping("/customers/{customerID}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerID, @RequestBody CustomerAddDTO toUpdate){
        return ResponseEntity
                .status(200)
                .body(customerService.updateCustomer(customerID,toUpdate));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable Long customerId){
        return ResponseEntity
                .status(200)
                .body(customerService.deleteById(customerId));
    }
}
