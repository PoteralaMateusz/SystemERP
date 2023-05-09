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

    @PostMapping("/orders")
    public ResponseEntity<?> addOrderWithCustomerId(@RequestBody Order toAdd){
        return orderService.addOrderWithCustomerId(toAdd);
    }
}
