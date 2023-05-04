package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> findItemsByProductId(Long id);

    Item save(Item item);

}
