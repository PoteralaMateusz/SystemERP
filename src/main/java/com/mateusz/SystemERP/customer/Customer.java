package com.mateusz.SystemERP.customer;

import com.mateusz.SystemERP.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@SQLDelete(sql = "UPDATE CUSTOMERS SET deleted = true WHERE id = ?")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    private boolean deleted = Boolean.FALSE;

    public Customer(String name, String country, String city, String street, Integer houseNumber, Integer zipCode, List<Order> orders) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.orders = orders;
    }

    public Customer(Long id, String name, String country, String city, String street, Integer houseNumber, Integer zipCode, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.orders = orders;
    }
}
