package com.mateusz.SystemERP.order;

import com.mateusz.SystemERP.order.dto.OrderAddDTO;
import com.mateusz.SystemERP.order.dto.OrderDTO;
import com.mateusz.SystemERP.order.dto.OrderStatsDTO;
import com.mateusz.SystemERP.order.dto.OrderUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity
                .status(200)
                .body(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(orderService.findOrderById(id));
    }

    @GetMapping("/orders/customer/{name}")
    public ResponseEntity<List<OrderDTO>> findOrdersByCustomerName(@PathVariable String name) {
        return ResponseEntity
                .status(200)
                .body(orderService.findOrdersByCustomerName(name));
    }

    @GetMapping("/orders/order_number/{orderNumber}")
    public ResponseEntity<OrderDTO> findOrderByOrderNumber(@PathVariable String orderNumber) {
        return ResponseEntity
                .status(200)
                .body(orderService.findOrderByOrderNumber(orderNumber));
    }

    @PostMapping("/orders/done/{orderId}")
    public ResponseEntity<OrderDTO> setFinishDateInOrderForCurrent(@PathVariable Long orderId) {
        return ResponseEntity
                .status(200)
                .body(orderService.setFinishDateWhenOrderIsDoneForCurrent(orderId));
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> deleteOrderByID(@PathVariable Long orderId) {
        return ResponseEntity
                .status(200)
                .body(orderService.deleteOrderByID(orderId));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderAddDTO orderAddDTO) {
        return ResponseEntity
                .status(200)
                .body(orderService.saveAll(orderAddDTO));
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderUpdateDTO orderUpdateDTO) {
        return ResponseEntity
                .status(200)
                .body(orderService.updateOrder(orderId, orderUpdateDTO));
    }

    @GetMapping("/orders/stats/{orderId}")
    public ResponseEntity<OrderStatsDTO> getOrderStats(@PathVariable Long orderId) {
        return ResponseEntity
                .status(200)
                .body(orderService.getOrderStats(orderId));
    }

    @GetMapping("/orders/stats")
    public ResponseEntity<List<OrderStatsDTO>> getOrdersStats() {
        return ResponseEntity
                .status(200)
                .body(orderService.getOrdersStats());
    }



}
