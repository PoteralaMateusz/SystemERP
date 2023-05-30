package com.mateusz.SystemERP.item;

import com.mateusz.SystemERP.item.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;


    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> findAllItems() {
        return ResponseEntity
                .status(200)
                .body(service.findAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<ItemDTO>> findItemsByProductId(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(service.findItemsByProductId(id));
    }

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addOrUpdateItem(@RequestBody ItemDTO toAdd) {
        return ResponseEntity
                .status(200)
                .body(service.addItem(toAdd));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<ItemDTO> deleteItem(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(service.deleteItemByID(id));
    }
}
