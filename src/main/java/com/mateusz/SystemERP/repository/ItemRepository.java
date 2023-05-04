package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> findItemsByProductId(Long id);

    void addItem(Item item);

}
