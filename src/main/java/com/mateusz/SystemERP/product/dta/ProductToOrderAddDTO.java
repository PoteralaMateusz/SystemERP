package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;

import java.util.List;

public record ProductToOrderAddDTO(
        String drawingName,
        Integer pieces,
        List<ItemAddDTO> items
) {
}
