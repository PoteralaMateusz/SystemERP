package com.mateusz.SystemERP.customer.dto;

public record CustomerAddDTO(
        String name,
        String country,
        String city,
        String street,
        Integer houseNumber,
        Integer zipCode
) {
}
