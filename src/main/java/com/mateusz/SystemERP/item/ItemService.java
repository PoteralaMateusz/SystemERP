package com.mateusz.SystemERP.item;

import com.mateusz.SystemERP.item.dto.ItemAddDTO;
import com.mateusz.SystemERP.item.dto.ItemDTO;
import com.mateusz.SystemERP.item.dto.ItemDTOMapper;
import com.mateusz.SystemERP.item.dto.ItemUpdateDTO;
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

import static com.mateusz.SystemERP.calculations.WeightCalculation.calculateProductTotalWeight;

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
    public ItemDTO addItem(ItemAddDTO itemAddDTO) {
        Optional<Product> product = productRepository.findProductById(itemAddDTO.productId());
        if (product.isEmpty()) {
            throw new ProductNotFoundException(itemAddDTO.productId());
        }
        Item itemToAdd = itemDTOMapper.mapAddDTO(itemAddDTO);
        itemToAdd.setProduct(product.get());
        itemRepository.save(itemToAdd);
        product.ifPresent(productWeightUpdate ->
                productWeightUpdate.setTotalWeight(calculateProductTotalWeight(productWeightUpdate)));
        return itemDTOMapper.map(itemToAdd);

    }

    @Transactional
    public ItemDTO updateItem(Long itemId, ItemUpdateDTO itemUpdateDTO) {
        Item itemToUpdate = itemRepository.findItemById(itemId)
                .map(item -> {
                    if (itemUpdateDTO.material() != null) {
                        item.setMaterial(itemUpdateDTO.material());
                    }
                    if (itemUpdateDTO.quality() != null) {
                        item.setQuality(itemUpdateDTO.quality());
                    }
                    if (itemUpdateDTO.pieces() != null) {
                        item.setPieces(itemUpdateDTO.pieces());
                    }
                    if (itemUpdateDTO.weight() != null) {
                        item.setWeight(itemUpdateDTO.weight());
                    }
                    return itemRepository.save(item);
                }).orElseThrow(() ->
                        new ItemNotFoundException(itemId));
        itemToUpdate.getProduct().setTotalWeight(
                calculateProductTotalWeight(itemToUpdate.getProduct())
        );

        return itemDTOMapper.map(itemToUpdate);
    }

    public ItemDTO deleteItemByID(Long itemId) {
        Item itemToDelete = itemRepository.findItemById(itemId)
                .map(item -> {
                    itemRepository.deleteById(itemId);
                    productRepository.findProductById(item.getProduct().getId())
                            .map(product -> {
                                product.setTotalWeight(calculateProductTotalWeight(product));
                                return productRepository.save(product);
                            }).orElseThrow(() ->
                                    new ProductNotFoundException(item.getProduct().getId()));
                    return item;
                }).orElseThrow(() ->
                        new ItemNotFoundException(itemId));

        return itemDTOMapper.map(itemToDelete);
    }
}
