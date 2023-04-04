package com.emirhanarici.ecommerceproject.dto;

import lombok.Builder;

@Builder
public record InventoryResponse(
        String skuCode,
        boolean isInStock,
        int quantity
)
{


}
