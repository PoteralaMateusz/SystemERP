package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;

import java.util.List;

public record ProductAddDTO(
        String drawingName,
        String pieces,
        double totalWeight,
        List<ItemAddDTO> items
) {
}
