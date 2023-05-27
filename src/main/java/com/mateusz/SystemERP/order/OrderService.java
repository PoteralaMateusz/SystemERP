package com.mateusz.SystemERP.order;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.product.Product;
import com.mateusz.SystemERP.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    public ResponseEntity<List<Order>> getAllOrders() {
        if (orderRepository.findAll().isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(orderRepository.findAll());
    }

    public ResponseEntity<Order> findOrderById(Long id) {
        Optional<Order> result = orderRepository.findOrderById(id);
        if (result.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(result.get());
    }

    public ResponseEntity<List<Order>> findOrdersByCustomerName(String name) {
        List<Order> result = orderRepository.findOrdersByCustomerName(name);
        if (result.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(result);
    }

    public ResponseEntity<Order> findOrderByOrderNumber(String orderNumber) {
        Optional<Order> result = orderRepository.findOrderByOrderNumber(orderNumber);
        if (result.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(result.get());
    }

    public ResponseEntity<?> updateCustomerInOrderById(Long orderId, String customerId) {
        Optional<Customer> findCustomer = customerRepository.findCustomerByName(customerId);
        Optional<Order> findOrder = orderRepository.findOrderById(orderId);
        if (findOrder.isEmpty() || findCustomer.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        orderRepository.updateCustomerToOrderById(orderId, customerId);
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> addOrderWithCustomer(Order toAdd) {
        if (toAdd.getCustomer() == null || toAdd.getCustomer().getName() == null) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        findCustomerWhenDontExistSave(toAdd.getCustomer());
        orderRepository.addOrderWithCustomerId(
                toAdd.getOrderNumber(),
                toAdd.getDeadline(),
                toAdd.getFinishDate(),
                toAdd.getOrderDate(),
                toAdd.getPrice(),
                toAdd.getCustomer().getName()
        );
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> addOrderWithCustomerAndProducts(Order toAdd) {
        if (toAdd == null) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        findCustomerWhenDontExistSave(toAdd.getCustomer());

        orderRepository.addOrderWithCustomerId(
                toAdd.getOrderNumber(),
                toAdd.getDeadline(),
                toAdd.getFinishDate(),
                toAdd.getOrderDate(),
                toAdd.getPrice(),
                toAdd.getCustomer().getName()
        );
        Long orderId = orderRepository.findOrderByOrderNumber(toAdd.getOrderNumber()).get().getId();
        for (Product productToAdd : toAdd.getProducts()) {
            productRepository.addProductWithOrderId(
                    productToAdd.getDrawingName(),
                    productToAdd.getPieces(),
                    productToAdd.getTotalWeight(),
                    orderId
            );
        }

        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> setFinishDateWhenOrderIsDoneForCurrent(Long orderId) {
        Optional<Order> doneOrderToSetFinishDate = orderRepository.findOrderById(orderId);
        if (doneOrderToSetFinishDate.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        orderRepository.setFinishDateInOrder(orderId, LocalDateTime.now());
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> deleteOrderByID(Long id) {
        return orderRepository.findOrderById(id)
                .map(order -> {
                    orderRepository.deleteById(id);
                    return ResponseEntity
                            .status(200)
                            .build();
                }).orElseGet(() -> ResponseEntity
                        .status(404)
                        .build());
    }

    @Transactional
    public void saveAll(Order toSave) {
        Optional<Customer> customer = customerRepository.findCustomerByName(toSave.getCustomer().getName());
        if (customer.isEmpty()) {
            customerRepository.save(toSave.getCustomer());
        }
        customer.ifPresent(toSave::setCustomer);
        orderRepository.save(toSave);

        List<Product> productsToSave = toSave.getProducts();
        for (Product product : productsToSave) {
            product.setOrder(toSave);
            productRepository.save(product);

            List<Item> itemsToSave = product.getItems();
            for (Item item : itemsToSave) {
                item.setProduct(product);
                itemRepository.save(item);
            }
        }
    }


    private void findCustomerWhenDontExistSave(Customer customer) {
        customerRepository.findCustomerByName(customer.getName())
                .orElseGet(() -> customerRepository.save(customer));
    }
}
