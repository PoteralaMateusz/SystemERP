package com.mateusz.SystemERP.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();

    Optional<Order> findOrderById(Long id);

    List<Order> findOrdersByCustomerName(String name);

    Optional<Order> findOrderByOrderNumber(String orderNumber);

    Order save(Order order);

    void deleteById(Long id);

    void setFinishDateInOrder(Long orderId, LocalDate finishDate);

    void updateCustomerToOrderById(Long orderId, Long customerId);

    void addOrderWithCustomerId(String orderNumber, LocalDate deadline, LocalDate finishDate, LocalDate orderDate, BigDecimal price, Long customerId);
}
