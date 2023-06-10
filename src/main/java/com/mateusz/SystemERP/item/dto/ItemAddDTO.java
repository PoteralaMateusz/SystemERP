package com.mateusz.SystemERP.item.dto;

public record ItemAddDTO(
        String material,
        String quality,
        Integer pieces,
        Double weight
) {
}
