package com.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.entity.Order;

import jakarta.transaction.Transactional;

public interface OrderService {

	Order create(Order order) throws Exception;

	Order getOrderById(int id);

	List<Optional<Order>> getOrderByProductName(String name);

	Optional<Order> getOrderByStatus(String status);

	void updateOrderStatus(int id, String status);

	List<Order> getOrderByDate(LocalDate date);

	List<Object[]> getHighestOrderedProduct();

	String deleteByOrderId(int id);

	String deleteAll();

	List<Order> getAll();

	List<Order> getByCustomerId(int id);

}
