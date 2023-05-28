package com.mateusz.SystemERP.product.dta;

public record ProductDTO(
        Long id,
        String drawingName,
        String pieces,
        double totalWeight,
        Long orderId
) {
}
