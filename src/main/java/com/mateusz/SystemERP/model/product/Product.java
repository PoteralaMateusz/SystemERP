package com.mateusz.SystemERP.model.product;

import com.mateusz.SystemERP.model.order.Order;
import com.mateusz.SystemERP.model.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "product")
    private List<Item> items;
    private double totalWeight;

    public Product(Order order, double totalWeight) {
        this.order = order;
        this.totalWeight = totalWeight;
    }
}
