package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.item.dto.ItemDTOMapper;
import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDTOMapper {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ItemDTOMapper itemDTOMapper;

    public Product mapProductDTO(ProductDTO productDTO) {
        return new Product(
                productDTO.id(),
                productDTO.drawingName(),
                productDTO.pieces(),
                productDTO.totalWeight(),
                orderRepository.findOrderById(productDTO.orderId())
                        .orElseThrow(() ->
                                new OrderNotFoundException(productDTO.orderId())),
                productDTO.items()
                        .stream()
                        .map(itemDTOMapper::map)
                        .collect(Collectors.toList())
        );
    }

    public ProductDTO mapProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getDrawingName(),
                product.getPieces(),
                product.getTotalWeight(),
                product.getOrder().getId(),
                product.getItems()
                        .stream()
                        .map(itemDTOMapper::map)
                        .collect(Collectors.toList())
        );
    }

    public Product mapProductAddDTO(ProductAddDTO productAddDTO) {
        return new Product(
                null,
                productAddDTO.drawingName(),
                productAddDTO.pieces(),
                productAddDTO.totalWeight(),
                null,
                productAddDTO.items()
                        .stream()
                        .map(itemDTOMapper::mapAddDTO)
                        .collect(Collectors.toList())
        );
    }

    public ProductAddDTO mapProductAddDTO(Product product) {
        return new ProductAddDTO(
                product.getDrawingName(),
                product.getPieces(),
                product.getTotalWeight(),
                product.getItems()
                        .stream()
                        .map(itemDTOMapper::mapAddDTO)
                        .collect(Collectors.toList())
        );
    }
}
