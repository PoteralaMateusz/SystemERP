package com.mateusz.SystemERP.customer.dto;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDTOMapper {
    private final CustomerRepository customerRepository;

    public Customer map(CustomerDTO customerDTO){
        return new Customer(
                customerDTO.name(),
                customerDTO.country(),
                customerDTO.city(),
                customerDTO.street(),
                customerDTO.houseNumber(),
                customerDTO.zipCode(),
                null
        );
    }

    public CustomerDTO map(Customer customer){
        return new CustomerDTO(
                customer.getName(),
                customer.getCountry(),
                customer.getCity(),
                customer.getStreet(),
                customer.getHouseNumber(),
                customer.getZipCode()
        );
    }
}
