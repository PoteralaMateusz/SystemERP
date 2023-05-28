package com.mateusz.SystemERP.product.dta;

import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDTOMapper {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public Product map(ProductDTO productDTO) {
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

    public ProductDTO map(Product product){
        return new ProductDTO(
                product.getId(),
                product.getDrawingName(),
                product.getPieces(),
                product.getTotalWeight(),
                product.getOrder().getId()
        );
    }
}
