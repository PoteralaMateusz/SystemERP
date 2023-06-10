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
    private Double totalWeight;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "product")
    private List<Item> items;

}
