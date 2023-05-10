package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.model.order.OrderRepository;
import com.mateusz.SystemERP.model.product.Product;
import com.mateusz.SystemERP.model.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ResponseEntity<List<Product>> findAllProducts(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()){
            ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(products);
    }

    public ResponseEntity<List<Product>> findProductsByOrderId(Long orderId) {
        Optional<Order> orderToFind = orderRepository.findOrderById(orderId);
        if (orderToFind.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(productRepository.findProductsByOrderId(orderId));
    }

    public ResponseEntity<Product> createProductWithOrderId(Product toSave, Long orderId) {
        Optional<Order> orderToAddProduct = orderRepository.findOrderById(orderId);
        if (orderToAddProduct.isEmpty() || toSave == null) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        toSave.setOrder(orderToAddProduct.get());
        productRepository.addProductWithOrderId(toSave.getDrawingName(),toSave.getPieces(), toSave.getTotalWeight(), toSave.getOrder().getId());
        return ResponseEntity
                .status(201)
                .body(toSave);
    }

}
