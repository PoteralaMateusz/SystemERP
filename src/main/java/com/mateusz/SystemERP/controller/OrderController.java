package com.mateusz.SystemERP.controller;

import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return orderService.findOrderById(id);
    }

    @GetMapping("/orders/customer/{name}")
    public ResponseEntity<List<Order>> findOrdersByCustomerName(@PathVariable String name){
        return orderService.findOrdersByCustomerName(name);
    }

    @GetMapping("/orders/order_number/{orderNumber}")
    public ResponseEntity<Order> findOrderByOrderNumber(@PathVariable String orderNumber){
        return orderService.findOrderByOrderNumber(orderNumber);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> addOrderWithCustomer(@RequestBody Order toAdd){
        return orderService.addOrderWithCustomer(toAdd);
    }

    @PostMapping("/orders/full")
    public ResponseEntity<?> addOrderWithCustomerAndProducts(@RequestBody Order toAdd){
        return orderService.addOrderWithCustomerAndProducts(toAdd);
    }

    @PostMapping("/orders/done/{orderId}")
    public ResponseEntity<?> setFinishDateInOrderForCurrent(@PathVariable Long orderId){
        return orderService.setFinishDateWhenOrderIsDoneForCurrent(orderId);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrderByID(@PathVariable Long id){
        return orderService.deleteOrderByID(id);
    }
}
