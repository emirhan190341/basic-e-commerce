package com.emirhanarici.ecommerceproject.controller;



import com.emirhanarici.ecommerceproject.dto.OrderRequest;
import com.emirhanarici.ecommerceproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        orderService.placeOrder(orderRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Order Placed successfully.");
    }

}
