package com.mateusz.SystemERP.service;

import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.model.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public ResponseEntity<Order> findOrderById(Long id){
        Optional<Order> result = orderRepository.findOrderById(id);
        if (result.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(result.get());
    }

    public ResponseEntity<List<Order>> findOrdersByCustomerName(String name){
        List<Order> result = orderRepository.findOrdersByCustomerName(name);
        if (result.isEmpty()){
            return ResponseEntity
                    .status(404)
                    .build();
        }
        return ResponseEntity
                .status(200)
                .body(result);
    }
}
