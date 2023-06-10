package com.mateusz.SystemERP.product;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.item.dto.ItemDTOMapper;
import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.dta.ProductAddDTO;
import com.mateusz.SystemERP.product.dta.ProductDTO;
import com.mateusz.SystemERP.product.dta.ProductDTOMapper;
import com.mateusz.SystemERP.product.dta.ProductUpdateDTO;
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
    private final ItemRepository itemRepository;

    private final ProductDTOMapper productDTOMapper;
    private final ItemDTOMapper itemDTOMapper;

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
        if (orderRepository.findOrderById(orderId).isEmpty()) {
            throw new OrderNotFoundException(orderId);
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
    public ProductDTO createProduct(ProductAddDTO toSave) {
        List<Item> itemsToSave = toSave.items()
                .stream()
                .map(itemDTOMapper::mapAddDTO)
                .toList();
        toSave.items().clear();
        Product savedProduct = productRepository.save(productDTOMapper.mapProductAddDTO(toSave));

        for (Item item : itemsToSave) {
            item.setProduct(savedProduct);
            itemRepository.save(item);
            savedProduct.getItems().add(item);
        }

        return productDTOMapper.mapProductDTO(productRepository.findProductById(savedProduct.getId()).get());
    }

    @Transactional
    public ProductDTO updateProduct(Long productId, ProductUpdateDTO productUpdateDTO){
        return productRepository.findProductById(productId)
                .map(product -> {
                    if (productUpdateDTO.drawingName() != null){
                        product.setDrawingName(productUpdateDTO.drawingName());
                    }
                    if (productUpdateDTO.pieces() != null){
                        product.setPieces(productUpdateDTO.pieces());
                    }
                    if (productUpdateDTO.totalWeight() != null){
                        product.setTotalWeight(productUpdateDTO.totalWeight());
                    }
                    return productDTOMapper.mapProductDTO(productRepository.save(product));
                }).orElseThrow(() ->
                        new ProductNotFoundException(productId));
    }

    public ProductDTO deleteProductById(Long productId) {
        return productRepository.findProductById(productId)
                .map(product -> {
                    productRepository.deleteById(product.getId());
                    return productDTOMapper.mapProductDTO(product);
                })
                .orElseThrow(() ->
                        new ProductNotFoundException("Products with orderID " + productId + " does not exist."));
    }

}
