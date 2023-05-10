package com.mateusz.SystemERP.model.item;

import com.mateusz.SystemERP.model.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlItemRepository extends ItemRepository, JpaRepository<Item, Long> {

    List<Item> findItemsByProductId(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ITEMS (MATERIAL, PIECES, QUALITY, WEIGHT, PRODUCT_ID) " +
            "VALUES (:material,:pieces,:quality,:weight,:productId )", nativeQuery = true)
    void addItemWithProductId(@Param("material") String material,
                              @Param("pieces") int pieces,
                              @Param("quality") String quality,
                              @Param("weight") double weight,
                              @Param("productId") Long productId);

}
