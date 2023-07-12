package com.mateusz.SystemERP.item;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;
import com.mateusz.SystemERP.item.dto.ItemDTO;
import com.mateusz.SystemERP.item.dto.ItemUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> findAllItems() {
        return ResponseEntity
                .status(200)
                .body(itemService.findAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> findItemById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(itemService.findItemById(id));
    }

    @GetMapping("/items/product/{id}")
    public ResponseEntity<List<ItemDTO>> findItemsByProductId(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(itemService.findItemsByProductId(id));
    }

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemAddDTO toAdd) {
        return ResponseEntity
                .status(200)
                .body(itemService.addItem(toAdd));
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long itemId, @RequestBody ItemUpdateDTO toUpdate) {
        return ResponseEntity
                .status(200)
                .body(itemService.updateItem(itemId, toUpdate));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<ItemDTO> deleteItem(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(itemService.deleteItemByID(id));
    }
}
