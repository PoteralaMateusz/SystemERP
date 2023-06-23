package com.mateusz.SystemERP.item.dto;

public record ItemDTO(
        Long id,
        Long productId,
        String material,
        String quality,
        Integer pieces,
        Integer donePieces,
        Double weight
) {
}
