package com.mateusz.SystemERP.order.dto;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.product.dta.ProductAddDTO;
import com.mateusz.SystemERP.product.dta.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderAddDTO(
        CustomerDTO customer,
        String orderNumber,
        LocalDateTime orderDate,
        LocalDateTime deadline,
        LocalDateTime finishDate,
        BigDecimal price,
        List<ProductAddDTO> products
) {
}
