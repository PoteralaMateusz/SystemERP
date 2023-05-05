package com.mateusz.SystemERP.model.item;

import com.mateusz.SystemERP.model.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String material;
    private String quality;
    private int pieces;
    private double weight;

    public Item(Product product, String material, String quality, int pieces, double weight) {
        this.product = product;
        this.material = material;
        this.quality = quality;
        this.pieces = pieces;
        this.weight = weight;
    }
}
