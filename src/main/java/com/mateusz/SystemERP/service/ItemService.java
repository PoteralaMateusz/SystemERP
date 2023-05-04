package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.entity.Item;
import com.mateusz.SystemERP.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
