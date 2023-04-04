package com.emirhanarici.ecommerceproject.dto;



import com.emirhanarici.ecommerceproject.entity.Product;

import java.math.BigDecimal;

public record ProductResponse(
        String id,

        String name,
        String description,
        BigDecimal price
) {

    public static ProductResponse convertToProductResponse(Product from) {
        return new ProductResponse(from.getId(), from.getName(), from.getDescription(), from.getPrice());
    }

}
