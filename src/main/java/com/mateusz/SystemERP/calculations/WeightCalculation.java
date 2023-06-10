package com.mateusz.SystemERP.calculations;

import com.mateusz.SystemERP.item.Item;
import com.mateusz.SystemERP.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeightCalculation {

    static public Double calculateProductTotalWeight(Product product) {
        return product.getItems()
                .stream()
                .map(Item::getWeight)
                .reduce(Double::sum)
                .orElse(0D);

    }
}
