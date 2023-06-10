package com.mateusz.SystemERP.item;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;
import com.mateusz.SystemERP.item.dto.ItemDTO;
import com.mateusz.SystemERP.item.dto.ItemDTOMapper;
import com.mateusz.SystemERP.item.exceptions.ItemNotFoundException;
import com.mateusz.SystemERP.product.Product;
import com.mateusz.SystemERP.product.ProductRepository;
import com.mateusz.SystemERP.product.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final ItemDTOMapper itemDTOMapper;

    public List<ItemDTO> findAllItems() {
        List<Item> itemsList = itemRepository.findAll();
        if (itemsList.isEmpty()) {
            throw new ItemNotFoundException("Items list is empty");
        }
        return itemsList
                .stream()
                .map(itemDTOMapper::map)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> findItemsByProductId(Long id) {
        List<Item> itemsByProductId = itemRepository.findItemsByProductId(id);
        if (itemsByProductId.isEmpty()) {
            throw new ItemNotFoundException("Not found items that have product id: " + id + ".");
        }
        return itemsByProductId
                .stream()
                .map(itemDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public ItemDTO addItem(ItemDTO itemDTOtoAdd) {
        Optional<Product> product = productRepository.findProductById(itemDTOtoAdd.productId());
        if (product.isEmpty()) {
            throw new ProductNotFoundException(itemDTOtoAdd.productId());
        }
        Item itemToAdd = itemDTOMapper.map(itemDTOtoAdd);
        itemToAdd.setProduct(product.get());
        itemRepository.save(itemToAdd);
        return itemDTOMapper.map(itemToAdd);

    }

    @Transactional
    public ItemDTO updateItem(Long itemId, ItemAddDTO itemAddDTO){
        return itemDTOMapper.map(itemRepository.findItemById(itemId)
                .map(item -> {
                    if (itemAddDTO.material() != null){
                        item.setMaterial(itemAddDTO.material());
                    }
                    if (itemAddDTO.quality() != null){
                        item.setQuality(itemAddDTO.quality());
                    }
                    if (itemAddDTO.pieces() != null){
                        item.setPieces(itemAddDTO.pieces());
                    }
                    if (itemAddDTO.weight() != null){
                        item.setWeight(itemAddDTO.weight());
                    }
                    return itemRepository.save(item);
                }).orElseThrow(() ->
                        new ItemNotFoundException(itemId)));
    }

    public ItemDTO deleteItemByID(Long itemId) {
        Optional<Item> itemToDelete = itemRepository.findItemById(itemId);
        if (itemToDelete.isEmpty()) {
            throw new ItemNotFoundException(itemId);
        }
        itemRepository.deleteById(itemToDelete.get().getId());
        return itemDTOMapper.map(itemToDelete.get());
    }
}
