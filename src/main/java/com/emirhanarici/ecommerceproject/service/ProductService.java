package com.emirhanarici.ecommerceproject.service;

import com.emirhanarici.ecommerceproject.dto.ProductCreateRequest;
import com.emirhanarici.ecommerceproject.dto.ProductResponse;
import com.emirhanarici.ecommerceproject.entity.Product;
import com.emirhanarici.ecommerceproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductResponse::convertToProductResponse)
                .collect(Collectors.toList());
    }


    public ProductResponse createOneProduct(ProductCreateRequest request) {

        Product product = productRepository.save(ProductCreateRequest.convert(request));
        logger.info("Product {} is saved.", product.getId());

        return ProductResponse.convertToProductResponse(product);

    }
}
