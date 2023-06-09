package com.mateusz.SystemERP.order;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order, Long> {

    List<Order> findOrdersByCustomerName(String name);

    Optional<Order> findOrderByOrderNumber(String orderNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDERS SET FINISH_DATE = :finishDate WHERE ID = :orderId", nativeQuery = true)
    void setFinishDateInOrder(@Param("orderId") Long orderId, @Param("finishDate") LocalDate finishDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDERS SET CUSTOMER_ID = :customerId WHERE ID = :orderId", nativeQuery = true)
    void updateCustomerToOrderById(@Param("orderId") Long orderId, @Param("customerId") Long customerId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDERS (ORDER_NUMBER,DEADLINE,FINISH_DATE,ORDER_DATE,PRICE,CUSTOMER_ID) VALUES (:orderNumber,:deadline,:finishDate,:orderDate,:price,:customerId)", nativeQuery = true)
    void addOrderWithCustomerId(@Param("orderNumber") String orderNumber,
                                @Param("deadline") LocalDate deadline,
                                @Param("finishDate") LocalDate finishDate,
                                @Param("orderDate") LocalDate orderDate,
                                @Param("price") BigDecimal price,
                                @Param("customerId") Long customerId);

}
