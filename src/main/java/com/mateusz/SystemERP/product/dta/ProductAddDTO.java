package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;

import java.util.List;

public record ProductAddDTO(
        String drawingName,
        Integer pieces,
        Double totalWeight,
        Long orderId,
        List<ItemAddDTO> items
) {
}
