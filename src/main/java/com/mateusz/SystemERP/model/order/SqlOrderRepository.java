package com.mateusz.SystemERP.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order,Long> {

    List<Order> findOrdersByCustomerName(String name);

}
