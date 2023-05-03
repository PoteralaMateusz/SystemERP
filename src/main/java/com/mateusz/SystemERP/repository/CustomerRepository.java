package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository {
    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerByName(String name);

    void addCustomer(Customer customer);
}
