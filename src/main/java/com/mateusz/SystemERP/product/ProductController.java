package com.mateusz.SystemERP.product;

import com.mateusz.SystemERP.product.dta.ProductAddDTO;
import com.mateusz.SystemERP.product.dta.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        return ResponseEntity
                .status(200)
                .body(productService
                        .findAllProducts());
    }

    @GetMapping("/products/{orderId}")
    public ResponseEntity<List<ProductDTO>> findProductByOrderId(@PathVariable Long orderId) {
        return ResponseEntity
                .status(200)
                .body(productService
                        .findProductsByOrderId(orderId));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProductByOrderId(@RequestBody ProductAddDTO toUpdate) {
        return ResponseEntity
                .status(200)
                .body(productService
                        .createProduct(toUpdate));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long productId) {
        return ResponseEntity
                .status(200)
                .body(productService
                        .deleteProductById(productId));
    }
}
