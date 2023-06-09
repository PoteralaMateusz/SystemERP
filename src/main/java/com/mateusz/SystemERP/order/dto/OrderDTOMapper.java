package com.mateusz.SystemERP.order.dto;

import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.customer.dto.CustomerDTOMapper;
import com.mateusz.SystemERP.customer.exceptions.CustomerNotFoundException;
import com.mateusz.SystemERP.order.Order;
import com.mateusz.SystemERP.product.dta.ProductDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDTOMapper {
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;
    private final ProductDTOMapper productDTOMapper;

    public Order mapOrderDTO(OrderDTO orderDTO){
        return new Order(
                orderDTO.id(),
                customerDTOMapper.mapCustomerDTO(orderDTO.customer()),
                orderDTO.orderNumber(),
                orderDTO.orderDate(),
                orderDTO.deadline(),
                orderDTO.finishDate(),
                orderDTO.price(),
                orderDTO.products()
                        .stream()
                        .map(productDTOMapper::mapProductDTO)
                        .collect(Collectors.toList())
        );
    }

    public OrderDTO mapOrderDTO(Order order){
        return new OrderDTO(
                order.getId(),
                customerDTOMapper.mapCustomerDTO(order.getCustomer()),
                order.getOrderNumber(),
                order.getOrderDate(),
                order.getDeadline(),
                order.getFinishDate(),
                order.getPrice(),
                order.getProducts()
                        .stream()
                        .map(productDTOMapper::mapProductDTO)
                        .collect(Collectors.toList())
        );
    }

    public Order mapOrderAddDTO(OrderAddDTO orderAddDTO){
        return new Order(
                null,
                customerRepository.findCustomerById(orderAddDTO.customerId())
                        .filter(customer -> !customer.isDeleted())
                        .orElseThrow(() ->
                                new CustomerNotFoundException("Customer with id " + orderAddDTO.customerId() + " does not exit")),
                orderAddDTO.orderNumber(),
                orderAddDTO.orderDate(),
                orderAddDTO.deadline(),
                orderAddDTO.finishDate(),
                orderAddDTO.price(),
                orderAddDTO.products()
                        .stream()
                        .map(productDTOMapper::mapProductAddDTO)
                        .collect(Collectors.toList())
        );
    }

    public OrderAddDTO mapOrderAddDTO(Order order){
        return new OrderAddDTO(
                order.getCustomer().getId(),
                order.getOrderNumber(),
                order.getOrderDate(),
                order.getDeadline(),
                order.getFinishDate(),
                order.getPrice(),
                order.getProducts()
                        .stream()
                        .map(productDTOMapper::mapProductAddDTO)
                        .collect(Collectors.toList())
        );
    }
}
