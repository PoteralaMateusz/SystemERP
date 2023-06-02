package com.mateusz.SystemERP.product;

import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.dta.ProductDTO;
import com.mateusz.SystemERP.product.dta.ProductDTOMapper;
import com.mateusz.SystemERP.product.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductDTOMapper productDTOMapper;

    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product list is empty.");
        }
        return products
                .stream()
                .map(productDTOMapper::mapProductDTO)
                .collect(Collectors.toList());


    }

    public List<ProductDTO> findProductsByOrderId(Long orderId) {
        if (orderRepository.findOrderById(orderId).isEmpty()){
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }
        List<Product> productsByOrderId = productRepository.findProductsByOrderId(orderId);
        if (productsByOrderId.isEmpty()) {
            throw new ProductNotFoundException("Products with orderID " + orderId + " does not exist.");
        }
        return productsByOrderId
                .stream()
                .map(productDTOMapper::mapProductDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO createOrUpdateProductWithOrderId(ProductDTO toSave) {
        if (orderRepository.findOrderById(toSave.orderId()).isEmpty()){
            throw new OrderNotFoundException("Order with id " + toSave.orderId() + " does not exist.");
        }

        return productDTOMapper.mapProductDTO(productRepository.save(productDTOMapper.mapProductDTO(toSave)));
    }

    public ProductDTO deleteProductById(Long productId){
        return productRepository.findProductById(productId)
                .map(product -> {
                    productRepository.deleteById(product.getId());
                    return productDTOMapper.mapProductDTO(product);
                })
                .orElseThrow(() ->
                        new ProductNotFoundException("Products with orderID " + productId + " does not exist."));
    }

}
