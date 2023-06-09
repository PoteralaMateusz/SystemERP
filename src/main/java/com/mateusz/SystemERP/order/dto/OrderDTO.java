package com.mateusz.SystemERP.order.dto;

import com.mateusz.SystemERP.customer.dto.CustomerDTO;
import com.mateusz.SystemERP.product.dta.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
        Long id,
        CustomerDTO customer,
        String orderNumber,
        LocalDate orderDate,
        LocalDate deadline,
        LocalDate finishDate,
        BigDecimal price,
        List<ProductDTO> products
) {
}
