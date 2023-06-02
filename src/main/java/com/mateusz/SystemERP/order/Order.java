package com.mateusz.SystemERP.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateusz.SystemERP.customer.Customer;
import com.mateusz.SystemERP.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String orderNumber;
    private LocalDateTime orderDate;
    private LocalDateTime deadline;
    private LocalDateTime finishDate;
    private BigDecimal price;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<Product> products;
}
