package com.emirhanarici.ecommerceproject.dto;


import com.emirhanarici.ecommerceproject.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequest(

        @NotEmpty(message = "Name should not be empty")
        String name,

        @NotEmpty(message = "Description should not be empty")
        @Size(min = 5, message = "Description should contains more than 5 characters.")
        String description,
        @Min(value = 0L, message = "The value must be positive")
        BigDecimal price
) {
    public static Product convert(ProductCreateRequest request) {
        return new Product(request.name, request.description, request.price);
    }
}
