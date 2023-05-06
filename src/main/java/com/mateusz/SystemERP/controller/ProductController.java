package com.mateusz.SystemERP.controller;

import com.mateusz.SystemERP.model.product.Product;
import com.mateusz.SystemERP.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/products/{orderId}")
    public ResponseEntity<List<Product>> findProductByOrderId(@PathVariable Long orderId){
        return service.findProductByOrderId(orderId);
    }

    @PostMapping("/products/{orderId}")
    public ResponseEntity<Product> createProductByOrderId(@PathVariable Long orderId , @RequestBody Product product){
        return service.createProductByOrderId(orderId,product);
    }
}
