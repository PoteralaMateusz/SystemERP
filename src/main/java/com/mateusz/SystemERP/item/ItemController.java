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
        return ResponseEntity
                .status(200)
                .body(service.findAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<Item>> findItemsByProductId(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(service.findItemsByProductId(id));
    }

    @PostMapping("/items")
    public ResponseEntity<Item> addItem( @RequestBody Item toAdd) {
        return ResponseEntity
                .status(200)
                .body(service.addItem(toAdd));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(service.deleteItemByID(id));
    }
}
