package com.mateusz.SystemERP.dataInitializer;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.order.Order;
import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.product.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void initData() {
        customersInit();
        ordersInit();
    }

    private void customersInit() {
        Customer customer1 = new Customer("MetalBud", "Poland", "Warszawa", "Mickiewicza", 14, 12345, null);
        Customer customer2 = new Customer("BridgeBuilding", "England", "London", "Main", 123, 12345, null);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }

    private void ordersInit() {
        Order order1 = new Order(
                null,
                null,
                "2023-005",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(36),
                LocalDate.now(),
                BigDecimal.valueOf(12500L),
                new ArrayList<>()
        );
        orderRepository.addOrderWithCustomerId(order1.getOrderNumber(), order1.getDeadline(), order1.getFinishDate(), order1.getOrderDate(), order1.getPrice(), "MetalBud");

    }

}
