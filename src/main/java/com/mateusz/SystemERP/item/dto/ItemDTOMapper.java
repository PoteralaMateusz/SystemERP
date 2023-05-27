package com.mateusz.SystemERP.item.dto;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.item.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemDTOMapper {
    private final ItemRepository itemRepository;
    public ItemDTO map(Item item){
        return new ItemDTO(
                item.getId(),
                item.getMaterial(),
                item.getQuality(),
                item.getPieces(),
                item.getWeight()
        );
    }

    public Item map(ItemDTO itemDTO){
        return new Item(
                itemDTO.id(),
                itemRepository.findItemById(itemDTO.id())
                        .orElseThrow(() -> new ItemNotFoundException("Item with id " + itemDTO.id() + " does not exist."))
                        .getProduct(),
                itemDTO.material(),
                itemDTO.quality(),
                itemDTO.pieces(),
                itemDTO.weight()
                );
    }
}
