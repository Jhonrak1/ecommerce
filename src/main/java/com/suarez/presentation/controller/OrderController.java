package com.suarez.presentation.controller;

import com.suarez.domain.model.Order;
import com.suarez.domain.model.OrderItem;
import com.suarez.domain.model.Product;
import com.suarez.domain.service.OrderService;
import com.suarez.domain.service.UserService;
import com.suarez.presentation.controller.dto.OrderItemResponseDto;
import com.suarez.presentation.controller.dto.OrderRequestDto;
import com.suarez.presentation.controller.dto.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Order Management API")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderDto) {
        // Convert DTO to domain object
        List<OrderItem> orderItems = orderDto.getItems().stream()
                .map(itemDto -> OrderItem.builder()
                        .product(Product.builder().id(itemDto.getProductId()).build())
                        .quantity(itemDto.getQuantity())
                        .build())
                .collect(Collectors.toList());

        Order createdOrder = orderService.createOrder(orderDto.getUserId(), orderItems);

        // Convert back to DTO
        OrderResponseDto responseDto = mapToDto(createdOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get order history for a user")
    public ResponseEntity<List<OrderResponseDto>> getUserOrderHistory(@PathVariable Long userId) {
        // Validate user exists
        userService.findById(userId);

        List<Order> orders = orderService.findOrdersByUserId(userId);
        List<OrderResponseDto> responseDtos = orders.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        OrderResponseDto responseDto = mapToDto(order);

        return ResponseEntity.ok(responseDto);
    }

    private OrderResponseDto mapToDto(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getItems().stream()
                .map(item -> OrderItemResponseDto.builder()
                        .id(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .subtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build())
                .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus().toString())
                .items(itemDtos)
                .build();
    }
}