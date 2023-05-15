package com.mateusz.SystemERP.model.order;

import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();
    Optional<Order> findOrderById(Long id);
    List<Order> findOrdersByCustomerName(String name);
    Optional<Order> findOrderByOrderNumber(String orderNumber);
    Order save(Order order);
    void setFinishDateInOrder(Long orderId, LocalDateTime finishDate);
    void addCustomerToOrderById(Long orderId, String customerId);
    void addOrderWithCustomerId(String orderNumber, LocalDateTime deadline, LocalDateTime finishDate, LocalDateTime orderDate, BigDecimal price, String customerId);
}
