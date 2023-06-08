package com.mateusz.SystemERP.order.dto;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.product.dta.ProductAddDTO;
import com.mateusz.SystemERP.product.dta.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderAddDTO(
        CustomerDTO customer,
        String orderNumber,
        LocalDate orderDate,
        LocalDate deadline,
        LocalDate finishDate,
        BigDecimal price,
        List<ProductAddDTO> products
) {
}
