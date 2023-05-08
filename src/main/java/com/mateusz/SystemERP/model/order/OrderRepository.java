package com.mateusz.SystemERP.model.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findOrderById(Long id);
    List<Order> findOrdersByCustomerName(String name);
    Order save(Order order);

    void addCustomerToOrderById(Long orderId, String customerId);

    void addOrderWithCustomerId(LocalDateTime deadline, LocalDateTime finishDate, LocalDateTime orderDate, BigDecimal price, String customerId);
}
