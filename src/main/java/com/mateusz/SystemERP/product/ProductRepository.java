package com.mateusz.SystemERP.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByOrderId(Long id);

    Product save(Product product);

    void deleteById(Long id);

    void addProductWithOrderId(String drawingName, String pieces, double totalWeight, long orderID);
}
