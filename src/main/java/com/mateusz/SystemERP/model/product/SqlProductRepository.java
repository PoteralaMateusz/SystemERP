package com.mateusz.SystemERP.model.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Long> {

    List<Product> findProductsByOrderId(Long id);

    Optional<Product> findProductById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PRODUCTS (DRAWING_NAME,PIECES,TOTAL_WEIGHT,ORDER_ID)" +
            "VALUES (:drawingName,:pieces,:totalWeight,:orderID)", nativeQuery = true)
    void addProductWithOrderId(@Param("drawingName") String drawingName,
                               @Param("pieces") String pieces,
                               @Param("totalWeight") double totalWeight,
                               @Param("orderID") long orderID);

}
