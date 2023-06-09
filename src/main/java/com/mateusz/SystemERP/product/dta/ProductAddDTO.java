package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;

import java.util.List;

public record ProductAddDTO(
        String drawingName,
        Integer pieces,
        Long orderId,
        List<ItemAddDTO> items
) {
}
