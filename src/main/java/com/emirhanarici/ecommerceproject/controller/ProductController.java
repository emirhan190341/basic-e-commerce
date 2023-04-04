package com.emirhanarici.ecommerceproject.controller;


import com.emirhanarici.ecommerceproject.dto.ProductCreateRequest;
import com.emirhanarici.ecommerceproject.dto.ProductResponse;
import com.emirhanarici.ecommerceproject.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createOneProduct(@RequestBody @Valid ProductCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createOneProduct(request));
    }

}
