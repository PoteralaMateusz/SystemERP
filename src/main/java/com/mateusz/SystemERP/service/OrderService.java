package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.model.customer.Customer;
import com.mateusz.SystemERP.model.customer.CustomerRepository;
import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.model.order.OrderRepository;
import com.mateusz.SystemERP.model.product.Product;
import com.mateusz.SystemERP.model.product.ProductRepository;
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

    public ResponseEntity<Order> findOrderByOrderNumber(String orderNumber){
        Optional<Order> result = orderRepository.findOrderByOrderNumber(orderNumber);
        if (result.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(result.get());
    }

    public ResponseEntity<?> addCustomerToOrderById(Long orderId, String customerId) {
        Optional<Customer> findCustomer = customerRepository.findCustomerByName(customerId);
        Optional<Order> findOrder = orderRepository.findOrderById(orderId);
        if (findOrder.isEmpty() || findCustomer.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
        orderRepository.addCustomerToOrderById(orderId, customerId);
        return ResponseEntity
                .status(200)
                .build();
    }

    public ResponseEntity<?> addOrderWithCustomerId(Order toAdd) {
        if (customerRepository.findCustomerByName(toAdd.getCustomer().getName()).isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
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
        if (customerRepository.findCustomerByName(toAdd.getCustomer().getName()).isEmpty()) {
            customerRepository.save(toAdd.getCustomer());
        }
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

    public ResponseEntity<?> setFinishDateWhenOrderIsDoneForCurrent(Long orderId){
        Optional<Order> doneOrderToSetFinishDate = orderRepository.findOrderById(orderId);
        if (doneOrderToSetFinishDate.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        orderRepository.setFinishDateInOrder(orderId,LocalDateTime.now());
        return ResponseEntity
                .status(200)
                .build();
    }
}
