package com.mateusz.SystemERP.model.item;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository {

    List<Item> findAll();

    List<Item> findItemsByProductId(Long id);

    void addItemWithProductId(String material, int pieces, String quality, double weight, Long productId);

}
