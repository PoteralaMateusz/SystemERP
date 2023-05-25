package com.mateusz.SystemERP.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts(){
        return service.findAllProducts();
    }

    @GetMapping("/products/{orderId}")
    public ResponseEntity<List<Product>> findProductByOrderId(@PathVariable Long orderId){
        return service.findProductsByOrderId(orderId);
    }

    @PostMapping("/products/{orderId}")
    public ResponseEntity<Product> createProductByOrderId(@PathVariable Long orderId , @RequestBody Product product){
        return service.createProductWithOrderId(product, orderId);
    }
}
