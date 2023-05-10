package com.mateusz.SystemERP.model.product;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    List<Product> findProductsByOrderId(Long id);

    void addProductWithOrderId(String drawingName, String quality, double totalWeight, long orderID);
}
