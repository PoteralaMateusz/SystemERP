package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.customer.exceptions.CustomerExistException;
import com.mateusz.SystemERP.customer.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = repository.findAll();
        if (customerList.isEmpty()) {
            throw new CustomerNotFoundException("Customers list is empty.");
        }
        return customerList;
    }

    public Customer getCustomerByName(String name) {
        return repository
                .findCustomerByName(name)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with name " + name + " does not exist"));
    }

//    public ResponseEntity<Customer> addCustomer(String name) {
//        if (name.isBlank()) {
//            return ResponseEntity
//                    .status(400)
//                    .build();
//        }
//        if(repository.findCustomerByName(name).isPresent()){
//            return ResponseEntity
//                    .status(409)
//                    .build();
//        }
//        repository.save(new Customer(name));
//        return ResponseEntity
//                .status(201)
//                .build();
//    }

    public Customer addCustomer(String name) {
        if (name.isBlank()){
            throw new CustomerNotFoundException("Customer name is empty.");
        }
        if (repository.findCustomerByName(name).isPresent()){
            throw new CustomerExistException("Customer with name " + name + " is already exist.");
        }
        return repository.save(new Customer(name));
    }


}
