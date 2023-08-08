package com.mateusz.SystemERP.calculations;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.order.Order;
import com.mateusz.SystemERP.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightCalculationTest {

    @Test
    void calculateProductTotalWeight() {
        //given
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1L,null,"HEB 100","S235JR", 5, 0 , 15.0));
        itemList.add(new Item(2L,null,"HEB 120","S235JR", 1, 0 , 210.0));
        itemList.add(new Item(3L,null,"HEB 150","S235JR", 10, 0 , 100.0));
        Product product = new Product(1L,"Construction", 2 , null , null , itemList);
        //when
        Double result = WeightCalculation.calculateProductTotalWeight(product);
        //then
        assertEquals(1285,result);
    }

    @Test
    void calculateOrderWeight() {
        //given
        List<Item> itemListConstruction1 = new ArrayList<>();
        itemListConstruction1.add(new Item(1L,null,"HEB 100","S235JR", 5, 0 , 15.0));
        itemListConstruction1.add(new Item(2L,null,"HEB 120","S235JR", 1, 0 , 210.0));
        itemListConstruction1.add(new Item(3L,null,"HEB 150","S235JR", 10, 0 , 100.0));
        Product product1 = new Product(1L,"Construction 1", 1 , null , null , itemListConstruction1);
        product1.setTotalWeight(WeightCalculation.calculateProductTotalWeight(product1));

        List<Item> itemListConstruction2 = new ArrayList<>();
        itemListConstruction2.add(new Item(1L,null,"HEB 100","S235JR", 5, 0 , 10.0));
        Product product2 = new Product(1L,"Construction 2", 2 , null , null , itemListConstruction2);
        product2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(product2));

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Order order = new Order(1L,null,"11-123",null,null,null, BigDecimal.valueOf(123123),productList);

        //when
        Double result = WeightCalculation.calculateOrderWeight(order);

        //then
        assertEquals(1385,result);

    }

    @Test
    void calculateOrderDoneWeight() {
        //given
        List<Item> itemListConstruction1 = new ArrayList<>();
        itemListConstruction1.add(new Item(1L,null,"HEB 100","S235JR", 5, 0 , 15.0));
        itemListConstruction1.add(new Item(2L,null,"HEB 120","S235JR", 1, 0 , 210.0));
        itemListConstruction1.add(new Item(3L,null,"HEB 150","S235JR", 10, 3 , 100.0));
        Product product1 = new Product(1L,"Construction 1", 1 , null , null , itemListConstruction1);
        product1.setTotalWeight(WeightCalculation.calculateProductTotalWeight(product1));

        List<Item> itemListConstruction2 = new ArrayList<>();
        itemListConstruction2.add(new Item(1L,null,"HEB 100","S235JR", 5, 2 , 10.0));
        Product product2 = new Product(1L,"Construction 2", 2 , null , null , itemListConstruction2);
        product2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(product2));

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Order order = new Order(1L,null,"11-123",null,null,null, BigDecimal.valueOf(123123),productList);

        //when
        Double result = WeightCalculation.calculateOrderDoneWeight(order);

        //then
        assertEquals(340,result);
    }

    @Test
    void calculateWorkProgress() {
        //given
        List<Item> itemListConstruction1 = new ArrayList<>();
        itemListConstruction1.add(new Item(1L,null,"HEB 100","S235JR", 5, 0 , 15.0));
        itemListConstruction1.add(new Item(2L,null,"HEB 120","S235JR", 1, 0 , 210.0));
        itemListConstruction1.add(new Item(3L,null,"HEB 150","S235JR", 10, 3 , 100.0));
        Product product1 = new Product(1L,"Construction 1", 1 , null , null , itemListConstruction1);
        product1.setTotalWeight(WeightCalculation.calculateProductTotalWeight(product1));

        List<Item> itemListConstruction2 = new ArrayList<>();
        itemListConstruction2.add(new Item(1L,null,"HEB 100","S235JR", 5, 2 , 10.0));
        Product product2 = new Product(1L,"Construction 2", 2 , null , null , itemListConstruction2);
        product2.setTotalWeight(WeightCalculation.calculateProductTotalWeight(product2));

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Order order = new Order(1L,null,"11-123",null,null,null, BigDecimal.valueOf(123123),productList);

        //when
        Double result = WeightCalculation.calculateWorkProgress(order);

        //then
        assertEquals(1385,WeightCalculation.calculateOrderWeight(order));
        assertEquals(340,WeightCalculation.calculateOrderDoneWeight(order));
        assertEquals(24.548736462093864,result);
    }
}