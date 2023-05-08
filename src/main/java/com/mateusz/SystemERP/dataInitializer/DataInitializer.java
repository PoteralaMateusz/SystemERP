package com.mateusz.SystemERP.dataInitializer;

import com.mateusz.SystemERP.model.customer.Customer;
import com.mateusz.SystemERP.model.customer.CustomerRepository;
import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.model.order.OrderRepository;
import com.mateusz.SystemERP.model.product.Product;
import com.mateusz.SystemERP.model.product.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void initData(){
        customersInit();
        ordersInit();
        //productsInit();
    }

    private void customersInit(){
        Customer customer1 = new Customer("MetalBud");
        Customer customer2 = new Customer("BridgeBuild");
        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }

    private void ordersInit(){
        Order order1 = new Order(
                null,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().plusDays(36),
                LocalDateTime.now(),
                BigDecimal.valueOf(12500L)
        );
        orderRepository.addOrderWithCustomerId(order1.getDeadline(),order1.getFinishDate(),order1.getOrderDate(),order1.getPrice(),"MetalBud");
    }

    private void productsInit(){
        Product product1 = new Product(
                orderRepository.findOrderById(1L).get(),
                250
        );

        Product product2 = new Product(
                orderRepository.findOrderById(1L).get(),
                500
        );
        productRepository.save(product1);
        productRepository.save(product2);

    }

}
