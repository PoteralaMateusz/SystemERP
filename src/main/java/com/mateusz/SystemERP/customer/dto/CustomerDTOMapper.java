package com.mateusz.SystemERP.customer.dto;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDTOMapper {
    private final CustomerRepository customerRepository;

    public Customer mapCustomerAddDTO(CustomerAddDTO customerAddDTO){
        return new Customer(
                customerAddDTO.name(),
                customerAddDTO.country(),
                customerAddDTO.city(),
                customerAddDTO.street(),
                customerAddDTO.houseNumber(),
                customerAddDTO.zipCode(),
                null
        );
    }

    public CustomerAddDTO mapCustomerAddDTO(Customer customer){
        return new CustomerAddDTO(
                customer.getName(),
                customer.getCountry(),
                customer.getCity(),
                customer.getStreet(),
                customer.getHouseNumber(),
                customer.getZipCode()
        );
    }

    public Customer mapCustomerDTO(CustomerDTO customerDTO){
        return new Customer(
                customerDTO.customerId(),
                customerDTO.name(),
                customerDTO.country(),
                customerDTO.city(),
                customerDTO.street(),
                customerDTO.houseNumber(),
                customerDTO.zipCode(),
                null
        );
    }

    public CustomerDTO mapCustomerDTO(Customer customer){
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getCountry(),
                customer.getCity(),
                customer.getStreet(),
                customer.getHouseNumber(),
                customer.getZipCode()
        );
    }
}
