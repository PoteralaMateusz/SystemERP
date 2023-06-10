package com.mateusz.SystemERP.item.dto;

public record ItemUpdateDTO(
        String material,
        String quality,
        Integer pieces,
        Double weight
) {
}
