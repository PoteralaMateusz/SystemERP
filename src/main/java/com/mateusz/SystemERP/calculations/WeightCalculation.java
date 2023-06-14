package com.mateusz.SystemERP.calculations;

import com.mateusz.SystemERP.order.Order;
import com.mateusz.SystemERP.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeightCalculation {

    static public Double calculateProductTotalWeight(Product product) {
        return product.getItems()
                .stream()
                .map(item -> item.getWeight() * item.getPieces())
                .reduce(Double::sum)
                .orElse(0D);
    }

    static public Double calculateOrderWeight(Order order) {
        return order.getProducts().stream()
                .map(product -> product.getPieces() * product.getTotalWeight())
                .reduce(Double::sum)
                .orElse(0D);
    }

    static public Double calculateOrderLeftWeightToDone(Order order) {
        return order.getProducts()
                .stream()
                .map(product -> {
                    product.setItems(product.getItems()
                            .stream()
                            .filter(item -> !item.getProductionDone())
                            .collect(Collectors.toList()));
                    return product;
                })
                .toList().stream()
                .map(WeightCalculation::calculateProductTotalWeight)
                .reduce(Double::sum)
                .orElse(0D);
    }

    static public Double calculateWorkProgress(Order order) {
        return (1 - calculateOrderLeftWeightToDone(order) / calculateOrderWeight(order)) * 100;
    }
}
