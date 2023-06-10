package com.mateusz.SystemERP.order;

import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.customer.CustomerRepository;
import com.mateusz.SystemERP.customer.dto.CustomerDTOMapper;
import com.mateusz.SystemERP.customer.exceptions.CustomerNotFoundException;
import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.item.ItemRepository;
import com.mateusz.SystemERP.order.dto.OrderAddDTO;
import com.mateusz.SystemERP.order.dto.OrderDTO;
import com.mateusz.SystemERP.order.dto.OrderDTOMapper;
import com.mateusz.SystemERP.order.dto.OrderUpdateDTO;
import com.mateusz.SystemERP.order.exceptions.OrderNotFoundException;
import com.mateusz.SystemERP.product.Product;
import com.mateusz.SystemERP.product.ProductRepository;
import com.mateusz.SystemERP.product.dta.ProductDTOMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    private final OrderDTOMapper orderDTOMapper;
    private final CustomerDTOMapper customerDTOMapper;
    private final ProductDTOMapper productDTOMapper;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderDTOMapper::mapOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO findOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId)
                .map(orderDTOMapper::mapOrderDTO)
                .orElseThrow(() ->
                        new OrderNotFoundException(orderId));
    }

    public List<OrderDTO> findOrdersByCustomerName(String name) {
        return orderRepository.findOrdersByCustomerName(name)
                .stream()
                .map(orderDTOMapper::mapOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO findOrderByOrderNumber(String orderNumber) {
        return orderRepository.findOrderByOrderNumber(orderNumber)
                .map(orderDTOMapper::mapOrderDTO)
                .orElseThrow(() ->
                        new OrderNotFoundException("Orders with order number " + orderNumber + " does not exist"));
    }

    public OrderDTO updateCustomerInOrderById(Long orderId, String customerId) {
        return orderRepository.findOrderById(orderId)
                .map(order -> {
                    customerRepository.findCustomerByName(customerId)
                            .map(customer -> {
                                order.setCustomer(customer);
                                orderRepository.save(order);
                                return customer;
                            })
                            .orElseThrow(() ->
                                    new CustomerNotFoundException(customerId));
                    return orderDTOMapper.mapOrderDTO(order);
                }).orElseThrow(() ->
                        new OrderNotFoundException(orderId));

    }

    public OrderDTO setFinishDateWhenOrderIsDoneForCurrent(Long orderId) {

        return orderRepository.findOrderById(orderId)
                .map(order -> {
                    orderRepository.setFinishDateInOrder(orderId, LocalDate.now());
                    return orderDTOMapper.mapOrderDTO(order);
                }).orElseThrow(() ->
                        new OrderNotFoundException(orderId));

    }

    public OrderDTO deleteOrderByID(Long orderId) {
        return orderRepository.findOrderById(orderId)
                .map(order -> {
                    orderRepository.deleteById(orderId);
                    return orderDTOMapper.mapOrderDTO(order);
                })
                .orElseThrow(() ->
                        new OrderNotFoundException(orderId));
    }

    @Transactional
    public OrderDTO saveAll(OrderAddDTO orderAddDTO) {
        Customer customerToSave = orderDTOMapper.mapOrderAddDTO(orderAddDTO).getCustomer();
        List<Product> productsToSave = orderAddDTO.products()
                .stream()
                .map(productDTOMapper::mapProductToOrderAddDTO)
                .toList();
        Order orderToSave = orderDTOMapper.mapOrderAddDTO(orderAddDTO);
        orderToSave.setProducts(new ArrayList<>());

        orderToSave.setCustomer(customerToSave);
        Order addedOrder = orderRepository.save(orderToSave);

        for (Product productToSave : productsToSave) {
            List<Item> itemsToSave = productToSave.getItems();
            productToSave.setItems(new ArrayList<>());
            productToSave.setOrder(addedOrder);
            productRepository.save(productToSave);
            addedOrder.getProducts().add(productToSave);
            for (Item item : itemsToSave) {
                item.setProduct(productToSave);
                itemRepository.save(item);
                productToSave.getItems().add(item);
            }
        }

        return orderDTOMapper.mapOrderDTO(orderRepository.findOrderById(addedOrder.getId()).get());
    }

    @Transactional
    public OrderDTO updateOrder(Long orderId, OrderUpdateDTO orderUpdateDTO) {
        return orderDTOMapper.mapOrderDTO(orderRepository.findOrderById(orderId)
                .map(order -> {
                    customerRepository.findCustomerById(orderUpdateDTO.customerId())
                            .map(customer -> {
                                order.setCustomer(customer);
                                return customer;
                            })
                            .orElseThrow(() ->
                                    new CustomerNotFoundException(orderUpdateDTO.customerId()));
                    if (orderUpdateDTO.orderNumber() != null){
                        order.setOrderNumber(orderUpdateDTO.orderNumber());
                    }
                    if (orderUpdateDTO.deadline() != null){
                        order.setDeadline(orderUpdateDTO.deadline());
                    }
                    if (orderUpdateDTO.finishDate() != null){
                        order.setFinishDate(orderUpdateDTO.finishDate());
                    }
                    if (orderUpdateDTO.price() != null){
                        order.setPrice(orderUpdateDTO.price());
                    }
                    return orderRepository.save(order);
                })
                .orElseThrow(() ->
                        new OrderNotFoundException(orderId)));

    }
}
