package com.mateusz.SystemERP.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order,Long> {

    List<Order> findOrdersByCustomerName(String name);
}
