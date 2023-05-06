package com.mateusz.SystemERP.model.order;

import com.mateusz.SystemERP.model.customer.Customer;
import com.mateusz.SystemERP.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDateTime orderDate;
    private LocalDateTime deadline;
    private LocalDateTime finishDate;
    private BigDecimal price;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "order")
    private List<Product> products;

    public Order(Customer customer, LocalDateTime orderDate, LocalDateTime deadline, LocalDateTime finishDate, BigDecimal price) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.finishDate = finishDate;
        this.price = price;
    }
}
