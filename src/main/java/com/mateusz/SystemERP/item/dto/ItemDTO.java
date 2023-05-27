package com.mateusz.SystemERP.item.dto;

public record ItemDTO(
        Long id,
        Long productId,
        String material,
        String quality,
        int pieces,
        double weight
) {
}
