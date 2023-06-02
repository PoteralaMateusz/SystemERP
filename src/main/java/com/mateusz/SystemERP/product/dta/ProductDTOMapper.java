package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductDTOMapper {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public Product mapProductDTO(ProductDTO productDTO) {
        return new Product(
                productDTO.id(),
                productDTO.drawingName(),
                productDTO.pieces(),
                productDTO.totalWeight(),
                orderRepository.findOrderById(productDTO.orderId())
                        .orElseThrow(() ->
                                new OrderNotFoundException("Order with id " + productDTO.orderId() + " does not exist.")),
                itemRepository.findItemsByProductId(productDTO.id())
        );
    }

    public ProductDTO mapProductDTO(Product product){
        return new ProductDTO(
                product.getId(),
                product.getDrawingName(),
                product.getPieces(),
                product.getTotalWeight(),
                product.getOrder().getId()
        );
    }

    public Product mapProductAddDTO(ProductAddDTO productAddDTO) {
        return new Product(
                productAddDTO.id(),
                productAddDTO.drawingName(),
                productAddDTO.pieces(),
                productAddDTO.totalWeight(),
                null,
                new ArrayList<>()
        );
    }

    public ProductAddDTO mapProductAddDTO(Product product){
        return new ProductAddDTO(
                product.getId(),
                product.getDrawingName(),
                product.getPieces(),
                product.getTotalWeight()
        );
    }
}
