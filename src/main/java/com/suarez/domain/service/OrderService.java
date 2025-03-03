package com.suarez.domain.service;

import com.suarez.domain.model.Order;
import com.suarez.domain.model.OrderItem;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, List<OrderItem> items);
    List<Order> findOrdersByUserId(Long userId);
    Order findById(Long id);
}
