package com.mateusz.SystemERP.customer;


import java.util.List;
import java.util.Optional;


public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findCustomerByName(String name);

    Optional<Customer> findCustomerById(Long id);

    Customer save(Customer customer);

    void deleteById(Long id);
}
