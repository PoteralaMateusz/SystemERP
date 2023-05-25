package com.mateusz.SystemERP.item;

import com.mateusz.SystemERP.item.exceptions.ItemNotFoundException;
import com.mateusz.SystemERP.product.Product;
import com.mateusz.SystemERP.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    public List<Item> findAllItems() {
        List<Item> itemsList = itemRepository.findAll();
        if (itemsList.isEmpty()) {
            throw new ItemNotFoundException("Items list is empty");
        }
        return itemsList;
    }

    public List<Item> findItemsByProductId(Long id) {
        List<Item> itemsByProductId = itemRepository.findItemsByProductId(id);
        if (itemsByProductId.isEmpty()) {
            throw new ItemNotFoundException("Not found items that have product id: " + id + ".");
        }
        return itemsByProductId;
    }

    @Transactional
    public Item addItem(Item toAdd) {
        Optional<Product> product = productRepository.findProductById(toAdd.getProduct().getId());
        if (product.isEmpty()) {
            productRepository.save(toAdd.getProduct());
        }
        product.ifPresent(toAdd::setProduct);
        itemRepository.save(toAdd);
        return toAdd;

    }

    public Item deleteItemByID(Long id){
        Optional<Item> itemToDelete = itemRepository.findItemById(id);
        if (itemToDelete.isEmpty()){
            throw new ItemNotFoundException("Item with " + id + " id not found.");
        }
        itemRepository.deleteById(itemToDelete.get().getId());
        return itemToDelete.get();
    }
}
