package com.mateusz.SystemERP.model.customer;

import com.mateusz.SystemERP.model.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "customers")
public class Customer {

    @Id
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String name) {
        this.name = name;
    }
}
