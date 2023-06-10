package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.dto.ItemDTO;

import java.util.List;

public record ProductUpdateDTO(
        String drawingName,
        Integer pieces
) {
}
