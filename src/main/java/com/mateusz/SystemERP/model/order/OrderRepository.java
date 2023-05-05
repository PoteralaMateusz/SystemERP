package com.mateusz.SystemERP.model.order;

import java.util.List;

public interface OrderRepository {

    List<Order> findOrdersByCustomerName(String name);
    Order save(Order order);
}
