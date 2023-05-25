package com.mateusz.SystemERP.customer;


import java.util.List;
import java.util.Optional;


public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findCustomerByName(String name);

    Customer save(Customer customer);
}