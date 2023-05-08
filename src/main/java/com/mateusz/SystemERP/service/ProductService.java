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

    public ResponseEntity<Product> createProductByOrderId(Long orderId , Product product){
        Optional<Order> orderToAddProduct = orderRepository.findOrderById(orderId);
        if (orderToAddProduct.isEmpty() || product == null){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        product.setOrder(orderToAddProduct.get());
        productRepository.save(product);
        return ResponseEntity
                .status(201)
                .body(product);
    }

    public ResponseEntity<List<Product>> findProductByOrderId(Long orderId){
        Optional<Order> orderToFind = orderRepository.findOrderById(orderId);
        if (orderToFind.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(productRepository.findProductsByOrderId(orderId));
    }
}
