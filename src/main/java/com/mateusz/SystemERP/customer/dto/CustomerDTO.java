package com.mateusz.SystemERP.customer.dto;

public record CustomerDTO(
        Long customerId,
        String name,
        String country,
        String city,
        String street,
        Integer houseNumber,
        Integer zipCode
) {
}
