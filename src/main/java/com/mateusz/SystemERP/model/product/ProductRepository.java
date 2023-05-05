package com.mateusz.SystemERP.model.product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);
    List<Product> findProductsByOrderId(Long id);
}
