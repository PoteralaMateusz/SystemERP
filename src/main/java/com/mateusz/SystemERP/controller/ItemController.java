package com.mateusz.SystemERP.controller;

import com.mateusz.SystemERP.entity.Item;
import com.mateusz.SystemERP.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;

    @GetMapping("/items/{id}")
    public ResponseEntity<List<Item>> findItemsByProductId(@PathVariable Long id) {
        return service.findItemsByProductId(id);
    }

    @PostMapping("/items")
    public ResponseEntity<?> addItem(@RequestBody Item toAdd) {
        return service.addItem(toAdd);
    }
}
