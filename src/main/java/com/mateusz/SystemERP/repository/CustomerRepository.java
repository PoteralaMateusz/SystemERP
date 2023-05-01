package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final ICustomerRepository repository;

    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    public Optional<Customer> getCustomerByName(String name){
        return repository.findCustomerByName(name);
    }

    public void addCustomer(Customer customer){
        repository.save(customer);
    }
}
