package com.mateusz.SystemERP.model.order;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order,Long> {

    List<Order> findOrdersByCustomerName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDERS SET CUSTOMER_ID = :customerId WHERE ID = :orderId",nativeQuery = true)
    void updateCustomerOrderById(@Param("orderId") Long orderId,@Param("customerId") String customerId);

}
