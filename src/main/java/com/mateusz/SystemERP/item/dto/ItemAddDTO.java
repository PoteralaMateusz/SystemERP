package com.mateusz.SystemERP.item.dto;

public record ItemAddDTO(
        String material,
        String quality,
        int pieces,
        double weight
) {
}
