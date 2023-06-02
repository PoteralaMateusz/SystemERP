package com.mateusz.SystemERP.product.dta;

public record ProductAddDTO(
        Long id,
        String drawingName,
        String pieces,
        double totalWeight
) {
}
