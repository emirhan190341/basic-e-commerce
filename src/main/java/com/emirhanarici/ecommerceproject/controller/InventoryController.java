package com.emirhanarici.ecommerceproject.controller;


import com.emirhanarici.ecommerceproject.dto.InventoryResponse;
import com.emirhanarici.ecommerceproject.repository.InventoryRepository;
import com.emirhanarici.ecommerceproject.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam(required = false) List<String> skuCode) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryService.isInStock(skuCode));
    }


}
