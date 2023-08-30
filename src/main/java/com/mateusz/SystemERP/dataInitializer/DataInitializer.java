package com.mateusz.SystemERP.dataInitializer;

import com.mateusz.SystemERP.calculations.WeightCalculation;
import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.order.Order;
import com.mateusz.SystemERP.order.OrderRepository;
import com.mateusz.SystemERP.product.Product;
import com.mateusz.SystemERP.product.ProductRepository;
import com.mateusz.SystemERP.security.user.Role;
import com.mateusz.SystemERP.security.user.User;
import com.mateusz.SystemERP.security.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        userInit();
        customersInit();
        order1Init();
        order2Init();
        order3Init();
        order4Init();
        order5Init();
    }

    private void userInit(){
        User defaultUser = new User(null,"Mateusz","Poterala","Mateusz", passwordEncoder.encode( "123"), Role.TECHNOLOGY,new ArrayList<>());
        userRepository.save(defaultUser);
    }

    private void customersInit() {
        Customer customer1 = new Customer(null,"MetalBud", "Poland", "Warszawa", "Mickiewicza", 14, 12345, null);
        Customer customer2 = new Customer(null,"BridgeBuilding", "England", "London", "Main", 123, 12345, null);
        Customer customer3 = new Customer(null,"SteelMet", "Poland", "Kraków", "Polna", 44, 12546, null);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    private void order1Init() {
        Order order1 = new Order(
                null,
                null,
                "2023-005",
                LocalDate.now().minusDays(20),
                LocalDate.now().plusDays(5),
                null,
                BigDecimal.valueOf(12500L),
                new ArrayList<>()
        );
        order1.setCustomer(customerRepository.findCustomerById(1L).get());
        Order savedOrder = orderRepository.save(order1);

        Product product1 = new Product(null,"Hebel",2,0D,savedOrder,new ArrayList<>());
        Product savedProduct1 = productRepository.save(product1);
        savedOrder.getProducts().add(savedProduct1);
        Item item1 = new Item(null,savedProduct1,"Round 100","S355",1,1,125D);
        Item item2 = new Item(null,savedProduct1,"Plate 25","S355",2,1,3D);
        item1 = itemRepository.save(item1);
        product1.getItems().add(item1);
        item2 = itemRepository.save(item2);
        product1.getItems().add(item2);

        Product savedProduct2 = productRepository.save(product1);
        savedProduct2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(savedProduct2));
        productRepository.save(savedProduct2);
    }

    private void order2Init() {
        Order order1 = new Order(
                null,
                null,
                "2023-004",
                LocalDate.now().minusDays(40),
                LocalDate.now().minusDays(30),
                LocalDate.now().minusDays(30),
                BigDecimal.valueOf(122500L),
                new ArrayList<>()
        );
        order1.setCustomer(customerRepository.findCustomerById(2L).get());
        Order savedOrder = orderRepository.save(order1);

        Product product1 = new Product(null,"Construction 1",1,0D,savedOrder,new ArrayList<>());
        Product savedProduct1 = productRepository.save(product1);
        savedOrder.getProducts().add(savedProduct1);
        Item item1 = new Item(null,savedProduct1,"HEB120","S355",12,12,240D);
        Item item2 = new Item(null,savedProduct1,"Plate 15","S355",12,12,12D);
        Item item3 = new Item(null,savedProduct1,"Plate 10","S355",4,4,6D);
        item1 = itemRepository.save(item1);
        product1.getItems().add(item1);
        item2 = itemRepository.save(item2);
        product1.getItems().add(item2);
        item3 = itemRepository.save(item3);
        product1.getItems().add(item3);

        Product savedProduct2 = productRepository.save(product1);
        savedProduct2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(savedProduct2));
        productRepository.save(savedProduct2);

        Product product2 = new Product(null,"Construction 2",2,0D,savedOrder,new ArrayList<>());
        Product savedProduct3 = productRepository.save(product2);
        savedOrder.getProducts().add(savedProduct3);
        Item item4 = new Item(null, savedProduct3,"HEB100","S355",4,4,105D);
        Item item5 = new Item(null, savedProduct3,"Plate 10","S355",8,8,5D);
        item4 = itemRepository.save(item4);
        product2.getItems().add(item4);
        item5 = itemRepository.save(item5);
        product2.getItems().add(item5);

        Product savedProduct4 = productRepository.save(product2);
        savedProduct4.setTotalWeight(WeightCalculation.calculateProductTotalWeight(savedProduct4));
        productRepository.save(savedProduct4);
    }

    private void order3Init() {
        Order order1 = new Order(
                null,
                null,
                "2023-006",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(60),
                null,
                BigDecimal.valueOf(99500L),
                new ArrayList<>()
        );
        order1.setCustomer(customerRepository.findCustomerById(2L).get());
        Order savedOrder = orderRepository.save(order1);

        Product product1 = new Product(null, "Construction 1", 4, 0D, savedOrder, new ArrayList<>());
        Product savedProduct1 = productRepository.save(product1);
        savedOrder.getProducts().add(savedProduct1);
        Item item1 = new Item(null, savedProduct1, "IPE 80", "S355", 50, 25, 82D);
        Item item2 = new Item(null, savedProduct1, "L80x80x3", "S355", 12, 0, 23D);
        Item item3 = new Item(null, savedProduct1, "Plate 12", "S355", 22, 4, 8D);
        item1 = itemRepository.save(item1);
        product1.getItems().add(item1);
        item2 = itemRepository.save(item2);
        product1.getItems().add(item2);
        item3 = itemRepository.save(item3);
        product1.getItems().add(item3);

        Product savedProduct2 = productRepository.save(product1);
        savedProduct2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(savedProduct2));
        productRepository.save(savedProduct2);
    }

    private void order4Init() {
        Order order1 = new Order(
                null,
                null,
                "2023-007",
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(15),
                null,
                BigDecimal.valueOf(1500L),
                new ArrayList<>()
        );
        order1.setCustomer(customerRepository.findCustomerById(1L).get());
        Order savedOrder = orderRepository.save(order1);

        Product product1 = new Product(null,"Shaft",5,0D,savedOrder,new ArrayList<>());
        Product savedProduct1 = productRepository.save(product1);
        savedOrder.getProducts().add(savedProduct1);
        Item item1 = new Item(null,savedProduct1,"Round 20","S355",1,0,75D);
        Item item2 = new Item(null,savedProduct1,"Plate 10","S355",2,0,5D);
        item1 = itemRepository.save(item1);
        product1.getItems().add(item1);
        item2 = itemRepository.save(item2);
        product1.getItems().add(item2);

        Product savedProduct2 = productRepository.save(product1);
        savedProduct2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(savedProduct2));
        productRepository.save(savedProduct2);
    }

    private void order5Init() {
        Order order1 = new Order(
                null,
                null,
                "2023-015",
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                BigDecimal.valueOf(100L),
                new ArrayList<>()
        );
        order1.setCustomer(customerRepository.findCustomerById(3L).get());
        Order savedOrder = orderRepository.save(order1);

        Product product1 = new Product(null,"Screws",1,0D,savedOrder,new ArrayList<>());
        Product savedProduct1 = productRepository.save(product1);
        savedOrder.getProducts().add(savedProduct1);
        Item item1 = new Item(null,savedProduct1,"Nut M12","DIN 934",50,0,0.1D);
        Item item2 = new Item(null,savedProduct1,"Screw M12","Din 934",50,0,0.2D);
        Item item3 = new Item(null,savedProduct1,"Washer fi12","Din 125",150,0,0.05D);
        item1 = itemRepository.save(item1);
        product1.getItems().add(item1);
        item2 = itemRepository.save(item2);
        product1.getItems().add(item2);
        item3 = itemRepository.save(item3);
        product1.getItems().add(item3);

        Product savedProduct2 = productRepository.save(product1);
        savedProduct2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(savedProduct2));
        productRepository.save(savedProduct2);
    }

}
