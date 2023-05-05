package com.mateusz.SystemERP.model.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlItemRepository extends ItemRepository,JpaRepository<Item,Long> {

    List<Item> findItemsByProductId(Long id);

}
