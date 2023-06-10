package com.mateusz.SystemERP.item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> findAll();

    List<Item> findItemsByProductId(Long id);

    Optional<Item> findItemById(Long id);

    Item save(Item item);

    void deleteById(Long id);

    void addItemWithProductId(String material, Integer pieces, String quality, Double weight, Long productId);

}
