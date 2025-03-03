package com.suarez.infrastructure.repo.service;

import com.suarez.domain.model.Order;
import com.suarez.domain.model.OrderItem;
import com.suarez.domain.model.Product;
import com.suarez.domain.model.User;
import com.suarez.domain.model.enums.OrderStatus;
import com.suarez.domain.service.OrderService;
import com.suarez.infrastructure.repo.OrderRepo;
import com.suarez.infrastructure.repo.ProductRepo;
import com.suarez.infrastructure.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepository;
    private final UserRepo userRepository;
    private final ProductRepo productRepository;

    @Override
    public Order createOrder(Long userId, List<OrderItem> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Calculate total amount and validate product availability
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // Update stock
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            // Set product reference and price
            item.setProduct(product);
            item.setPrice(product.getPrice());

            // Add to total
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        // Create order
        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .totalAmount(totalAmount)
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);

        // Set order reference in items and save
        items.forEach(item -> item.setOrder(savedOrder));
        savedOrder.setItems(items);

        return orderRepository.save(savedOrder);
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }
}
