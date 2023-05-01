package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findCustomerByName(String name);
}
