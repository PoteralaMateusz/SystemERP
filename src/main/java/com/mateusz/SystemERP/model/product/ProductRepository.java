package com.mateusz.SystemERP.model.product;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByOrderId(Long id);

    void addProductWithOrderId(String drawingName, String pieces, double totalWeight, long orderID);
}
