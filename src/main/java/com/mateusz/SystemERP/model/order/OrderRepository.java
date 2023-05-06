package com.mateusz.SystemERP.model.order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findOrderById(Long id);
    List<Order> findOrdersByCustomerName(String name);
    Order save(Order order);
}
