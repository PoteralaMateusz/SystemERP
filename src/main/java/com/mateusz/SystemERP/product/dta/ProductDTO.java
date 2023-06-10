package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.dto.ItemDTO;

import java.util.List;

public record ProductDTO(
        Long id,
        String drawingName,
        String pieces,
        Double totalWeight,
        Long orderId,
        List<ItemDTO> items
) {
}
