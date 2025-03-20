package com.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.entity.Order;

public interface OrderService {
    void create(Order order);

    Order getOrderById(int id);

    Optional<Order> getOrderByProductName(String name);

    Optional<Order> getOrderByStatus(String status);

    void updateOrderStatus(int id, String status);

    List<Order> getOrderByDate(LocalDate startDate, LocalDate endDate);

    List<Object[]> getHighestOrderedProduct();

    String deleteByOrderId(int id);

    String deleteAll();
}
