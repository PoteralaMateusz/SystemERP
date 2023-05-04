package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SqlCustomerRepository extends CustomerRepository,JpaRepository<Customer,String> {

    Optional<Customer> findCustomerByName(String name);

}
