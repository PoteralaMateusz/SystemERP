package com.mateusz.SystemERP.item.dto;

public record ItemDTO(
        Long id,
        Long productId,
        String material,
        String quality,
        Integer pieces,
        Double weight,
        Boolean productionDone
) {
}
