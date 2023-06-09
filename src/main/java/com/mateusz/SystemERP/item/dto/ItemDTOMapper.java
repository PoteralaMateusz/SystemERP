package com.mateusz.SystemERP.item.dto;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.product.ProductRepository;
import com.mateusz.SystemERP.product.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemDTOMapper {

    private final ProductRepository productRepository;

    public ItemDTO map(Item item) {
        return new ItemDTO(
                item.getId(),
                item.getProduct().getId(),
                item.getMaterial(),
                item.getQuality(),
                item.getPieces(),
                item.getDonePieces(),
                item.getWeight()
        );
    }

    public Item map(ItemDTO itemDTO) {
        return new Item(
                itemDTO.id(),
                productRepository.findProductById(itemDTO.productId())
                        .orElseThrow(() ->
                                new ProductNotFoundException(itemDTO.productId())),
                itemDTO.material(),
                itemDTO.quality(),
                itemDTO.pieces(),
                itemDTO.donePieces(),
                itemDTO.weight()
        );
    }

    public Item mapAddDTO(ItemAddDTO itemAddDTO) {
        return new Item(
                null,
                null,
                itemAddDTO.material(),
                itemAddDTO.quality(),
                itemAddDTO.pieces(),
                0,
                itemAddDTO.weight()
        );
    }

    public ItemAddDTO mapAddDTO(Item item) {
        return new ItemAddDTO(
                item.getProduct().getId(),
                item.getMaterial(),
                item.getQuality(),
                item.getPieces(),
                item.getWeight()
        );
    }
}
