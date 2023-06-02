package com.mateusz.SystemERP.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateusz.SystemERP.order.Order;
import com.mateusz.SystemERP.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String drawingName;
    private String pieces;
    private double totalWeight;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "product")
    private List<Item> items;


    public Product(Order order, double totalWeight) {
        this.order = order;
        this.totalWeight = totalWeight;
    }
}
