package com.mateusz.SystemERP.customer.dto;

public record CustomerDTO(
        String name,
        String country,
        String city,
        String street,
        int houseNumber,
        int zipCode
) {
}
