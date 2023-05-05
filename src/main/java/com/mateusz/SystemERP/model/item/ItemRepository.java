package com.mateusz.SystemERP.model.item;

import java.util.List;

public interface ItemRepository {

    List<Item> findItemsByProductId(Long id);

    Item save(Item item);

}
