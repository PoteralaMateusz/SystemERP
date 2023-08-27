package com.mateusz.SystemERP.order;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.customer.dto.CustomerDTOMapper;
import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.item.dto.ItemDTOMapper;
import com.mateusz.SystemERP.order.dto.OrderDTO;
import com.mateusz.SystemERP.order.dto.OrderDTOMapper;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.ProductRepository;
import com.mateusz.SystemERP.product.dta.ProductDTOMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Test
    @DisplayName("Should throw OrderNotFoundException when order dont exists")
    void findByNotExistingOrderId() {
        //given
        OrderRepository mockOrderRepository = mock(OrderRepository.class);
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        ItemRepository mockItemRepository = mock(ItemRepository.class);
        CustomerDTOMapper customerDTOMapper = new CustomerDTOMapper(mockCustomerRepository);
        ItemDTOMapper itemDTOMapper = new ItemDTOMapper(mockProductRepository);
        ProductDTOMapper productDTOMapper = new ProductDTOMapper(mockOrderRepository,itemDTOMapper);
        OrderDTOMapper orderDTOMapper = new OrderDTOMapper(mockCustomerRepository,customerDTOMapper,productDTOMapper);

        OrderService orderService = new OrderService(mockOrderRepository,mockCustomerRepository,mockProductRepository,
                mockItemRepository,orderDTOMapper,customerDTOMapper,productDTOMapper);
        //when
        when(mockOrderRepository.findOrderById(anyLong())).thenReturn(Optional.empty());
        Long orderId = 1L;
        //then
        try {
            orderService.findOrderById(orderId);
        }
        catch (Exception e){
            assertThat(e)
                    .isInstanceOf(OrderNotFoundException.class)
                    .hasMessageContaining(orderId + " does not exist.");
        }
    }

    @Test
    @DisplayName("Should throw OrderNotFoundException when order dont exists")
    void findByExistingOrderId() {
        //given
        OrderRepository mockOrderRepository = mock(OrderRepository.class);
        CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        ItemRepository mockItemRepository = mock(ItemRepository.class);
        CustomerDTOMapper customerDTOMapper = new CustomerDTOMapper(mockCustomerRepository);
        ItemDTOMapper itemDTOMapper = new ItemDTOMapper(mockProductRepository);
        ProductDTOMapper productDTOMapper = new ProductDTOMapper(mockOrderRepository,itemDTOMapper);
        OrderDTOMapper orderDTOMapper = new OrderDTOMapper(mockCustomerRepository,customerDTOMapper,productDTOMapper);

        OrderService orderService = new OrderService(mockOrderRepository,mockCustomerRepository,mockProductRepository,
                mockItemRepository,orderDTOMapper,customerDTOMapper,productDTOMapper);
        Customer customer = new Customer(1L,"Customer","Country","City","Street",123,134,new ArrayList<>());
        Long orderId = 1L;
        Order orderToFind = new Order(orderId,customer,"05-2023", LocalDate.now(), LocalDate.now().plusDays(30L),null, BigDecimal.valueOf(12500),new ArrayList<>());
        //when
        when(mockOrderRepository.findOrderById(anyLong())).thenReturn(Optional.of(orderToFind));
        OrderDTO result = orderService.findOrderById(orderId);
        //then
        assertEquals(result.orderNumber(),"05-2023");
        assertEquals(result.orderDate(),LocalDate.now());
    }

    @Test
    void findOrderByOrderNumber() {
    }

    @Test
    void setFinishDateWhenOrderIsDoneForCurrent() {
    }

    @Test
    void getOrderStats() {
    }
}