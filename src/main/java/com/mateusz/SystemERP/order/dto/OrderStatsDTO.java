package com.mateusz.SystemERP.order.dto;

public record OrderStatsDTO(
        Long id,
        String customerName,
        String orderNumber,
        Long daysToDeadline,
        Double orderWeight,
        Double doneWeight,
        Double workProgress
) {
}
