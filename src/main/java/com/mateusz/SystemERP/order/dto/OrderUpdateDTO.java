package com.mateusz.SystemERP.order.dto;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.product.dta.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderUpdateDTO(
        Long customerId,
        String orderNumber,
        LocalDate deadline,
        LocalDate finishDate,
        BigDecimal price
) {
}
