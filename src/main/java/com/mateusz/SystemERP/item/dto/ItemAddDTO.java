package com.mateusz.SystemERP.item.dto;

public record ItemAddDTO(
        Long productId,
        String material,
        String quality,
        Integer pieces,
        Double weight
) {
}
