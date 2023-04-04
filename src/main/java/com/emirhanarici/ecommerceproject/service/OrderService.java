package com.emirhanarici.ecommerceproject.service;


import com.emirhanarici.ecommerceproject.dto.InventoryResponse;
import com.emirhanarici.ecommerceproject.dto.OrderLineItemsDto;
import com.emirhanarici.ecommerceproject.dto.OrderRequest;
import com.emirhanarici.ecommerceproject.dto.OrderResponse;
import com.emirhanarici.ecommerceproject.entity.Order;
import com.emirhanarici.ecommerceproject.entity.OrderLineItems;
import com.emirhanarici.ecommerceproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode).toList();

//        orderRepository.save(order);

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://localhost:8080/v1/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        List<Integer> numbers = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getQuantity).toList();

        List<Integer> responseNumbers = Arrays.stream(inventoryResponseArray)
                .map(InventoryResponse::quantity).toList();


        //Later will change to dynamic statement
        if (allProductsInStock && (numbers.get(0) <= responseNumbers.get(0))) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }


        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItemsList(orderLineItems)
                .build();

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
