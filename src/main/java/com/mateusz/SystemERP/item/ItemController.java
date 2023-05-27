package com.mateusz.SystemERP.item;

import com.mateusz.SystemERP.item.dto.ItemDTO;
import com.mateusz.SystemERP.item.dto.ItemDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;
    private final ItemDTOMapper itemDTOMapper;

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> findAllItems(){
        return ResponseEntity
                .status(200)
                .body(service.findAllItems()
                        .stream()
                        .map(itemDTOMapper::map)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<ItemDTO>> findItemsByProductId(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(service.findItemsByProductId(id)
                        .stream()
                        .map(itemDTOMapper::map)
                        .collect(Collectors.toList()));
    }

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addOrUpdateItem(@RequestBody ItemDTO toAdd) {
        Item itemAdded = service.addItem(itemDTOMapper.map(toAdd));
        return ResponseEntity
                .status(200)
                .body(itemDTOMapper.map(itemAdded));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<ItemDTO> deleteItem(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(itemDTOMapper.map(service.deleteItemByID(id)));
    }
}
