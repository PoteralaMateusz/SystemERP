package com.mateusz.SystemERP.model.order;

import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findOrderById(Long id);
    List<Order> findOrdersByCustomerName(String name);
    Order save(Order order);

    void updateCustomerOrderById(Long orderId,String customerId);

    void saveOrderWithCustomerId(LocalDateTime deadline, LocalDateTime finishDate, LocalDateTime orderDate, BigDecimal price, String customerId);
}
