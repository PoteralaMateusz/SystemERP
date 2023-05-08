package com.mateusz.SystemERP.model.customer;

import com.mateusz.SystemERP.model.order.Order;
import jakarta.persistence.*;

import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
