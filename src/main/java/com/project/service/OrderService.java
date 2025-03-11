package com.project.service;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Order;

import com.project.repository.OrderRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public List<Order> getOrderByDate(LocalDate startDate, LocalDate endDate) {
	return orderRepo.getOrderByDate(startDate,endDate);
}

public List<Object[]> getHighestOrderedProduct() {
	Pageable pageable=PageRequest.of(0, 1);
	return orderRepo.findHighestOrderedProduct(pageable);
}
public String deleteByOrderId(int id) {
orderRepo.deleteById(id);
	return "Successfully deleted";
}
public String deleteAll() {
	orderRepo.deleteAll();
	return "Deleted All entries";
}
}
