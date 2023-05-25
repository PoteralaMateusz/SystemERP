package com.mateusz.SystemERP.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> findAllItems(){
        return service.findAllItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<Item>> findItemsByProductId(@PathVariable Long id) {
        return service.findItemsByProductId(id);
    }

    @PostMapping("/items/{productId}")
    public ResponseEntity<?> addItemWithProductID(@PathVariable Long productId , @RequestBody Item toAdd) {
        return service.addItemWithProductId(productId, toAdd);
    }
}
