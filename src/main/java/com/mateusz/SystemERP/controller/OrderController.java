package com.mateusz.SystemERP.controller;

import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/orders/{name}")
    public ResponseEntity<List<Order>> findOrdersByCustomerName(@PathVariable String name){
        return orderService.findOrdersByCustomerName(name);
    }
}
