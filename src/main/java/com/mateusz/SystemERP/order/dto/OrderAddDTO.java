package com.mateusz.SystemERP.order.dto;

import com.mateusz.SystemERP.product.dta.ProductAddDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderAddDTO(
        Long customerId,
        String orderNumber,
        LocalDate orderDate,
        LocalDate deadline,
        LocalDate finishDate,
        BigDecimal price,
        List<ProductAddDTO> products
) {
}
