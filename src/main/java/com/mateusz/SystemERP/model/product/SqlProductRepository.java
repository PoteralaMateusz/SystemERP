package com.mateusz.SystemERP.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Long> {

    List<Product> findProductsByOrderId(Long id);

}
