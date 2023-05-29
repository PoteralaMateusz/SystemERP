package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    private String name;
    private String country;
    private String city;
    private String street;
    private int houseNumber;
    private int zipCode;

    @OneToMany(mappedBy = "customer")
    @Transient
    private List<Order> orders;

}
