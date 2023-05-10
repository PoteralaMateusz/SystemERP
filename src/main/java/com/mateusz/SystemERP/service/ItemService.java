package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.model.item.Item;
import com.mateusz.SystemERP.model.item.ItemRepository;
import com.mateusz.SystemERP.model.product.Product;
import com.mateusz.SystemERP.model.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<List<Item>> findAllItems(){
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(items);
    }

    public ResponseEntity<List<Item>> findItemsByProductId(Long id){
        List<Item> itemsByProductId = itemRepository.findItemsByProductId(id);
        if (itemsByProductId.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(itemsByProductId);
    }

    public ResponseEntity<?> addItemWithProductId(Long productId, Item toAdd){
        Optional<Product> product = productRepository.findProductById(productId);
        if (product.isEmpty() || toAdd == null){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        itemRepository.addItemWithProductId(toAdd.getMaterial(),toAdd.getPieces(),toAdd.getQuality(), toAdd.getWeight(), productId);
        return ResponseEntity
                .status(200)
                .build();

    }
}
