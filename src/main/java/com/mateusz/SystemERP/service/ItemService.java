package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.model.item.Item;
import com.mateusz.SystemERP.model.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;

    public ResponseEntity<List<Item>> findItemsByProductId(Long id){
        List<Item> itemsByProductId = repository.findItemsByProductId(id);
        if (itemsByProductId.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(itemsByProductId);
    }

    public ResponseEntity<?> addItem(Item toAdd){
        if (toAdd == null){
            return ResponseEntity
                    .status(400)
                    .build();
        }
        toAdd.setId(null);
        return ResponseEntity
                .status(200)
                .body(repository.save(toAdd));

    }
}
