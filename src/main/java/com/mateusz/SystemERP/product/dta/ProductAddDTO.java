package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.dto.ItemDTO;

import java.util.List;

public record ProductAddDTO(
        Long id,
        String drawingName,
        String pieces,
        double totalWeight,
        List<ItemDTO> items
) {
}
