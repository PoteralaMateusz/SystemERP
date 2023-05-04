package com.mateusz.SystemERP.repository;

import com.mateusz.SystemERP.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlItemRepository extends ItemRepository,JpaRepository<Item,Long> {

    List<Item> findItemsByProductId(Long id);

}
