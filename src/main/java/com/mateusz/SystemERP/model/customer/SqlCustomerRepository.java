package com.mateusz.SystemERP.model.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SqlCustomerRepository extends CustomerRepository, JpaRepository<Customer,String> {

    Optional<Customer> findCustomerByName(String name);

}
