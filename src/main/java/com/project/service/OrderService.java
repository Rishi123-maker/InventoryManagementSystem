package com.project.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Order;

import com.project.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
private OrderRepository orderRepo;
	public void create(Order order)
	{
		orderRepo.save(order);
	}
	public Order getOrderById(int id)
	{
		return orderRepo.findByOrderId(id);
	}
public Optional<Order>getOrderByProductName(String name)
{
	return orderRepo.findByProductName(name);
}
public Optional<Order>getOrderByStatus(String status)
{
	return orderRepo.findByStatus(status);
}
public void updateOrderStatus(int id,String status) {
	Order o=orderRepo.findByOrderId(id);
	if(o!=null)
	{
		o.setStatus(status);
	}
	orderRepo.save(o);
}
}
