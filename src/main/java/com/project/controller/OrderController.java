package com.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Order;
import com.project.service.OrderService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/orders")
public class OrderController {
@Autowired
private OrderService orderService;
@GetMapping("/")
public ResponseEntity<String> Testing()
{
	System.out.println("this is testing");
	return new ResponseEntity<>("Hi this is orders",HttpStatus.OK);
}
@GetMapping("/admin/getById/{id}")
public ResponseEntity<Order> getOrderByID(@PathVariable int id)
{
 return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
}
@PostMapping("/admin/create")
@Transactional
public ResponseEntity<String> create(@RequestBody Order o)
{
  orderService.create(o);
  return new ResponseEntity<>("Successfully created an entry",HttpStatus.OK);
}
@GetMapping("/admin/getOrderByProductName/{name}")
public ResponseEntity<Optional<Order>> getOrderByProductName(@PathVariable String name)
{
 return new ResponseEntity<>(orderService.getOrderByProductName(name),HttpStatus.OK);
}
@GetMapping("/amdin/getOrderByStatus/{status}")
public ResponseEntity<Optional<Order>> getOrderByStatus(@PathVariable String status)
{
	return new ResponseEntity<>(orderService.getOrderByStatus(status),HttpStatus.OK);
}
@PutMapping("/admin/updateOrderStatus")
public ResponseEntity<String> updateOrderStatus(@RequestParam int id, @RequestParam String status)
{
	orderService.updateOrderStatus(id,status);
	return new ResponseEntity<String>("Updation Successful",HttpStatus.OK);
}
@GetMapping("/admin/getOrderByDate")
public ResponseEntity<List<Order>> getOrderByDate(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate)
{
	return new ResponseEntity<>(orderService.getOrderByDate(startDate,endDate),HttpStatus.OK);
}
@GetMapping("/admin/getHighestOrderedProduct")
public ResponseEntity<List<Object[]>>getHighestOrderedProduct()
{
	return new ResponseEntity<>(orderService.getHighestOrderedProduct(),HttpStatus.OK);
}
@DeleteMapping("/customer/deleteByOrderId/{id}")
public ResponseEntity<String>deleteByOrderById(@PathVariable int id)
{    
	return new ResponseEntity<>(orderService.deleteByOrderId(id),HttpStatus.OK);
}
@DeleteMapping("/admin/deleteAll")
public ResponseEntity<String> deleteAll()
{
	return new ResponseEntity<>(orderService.deleteAll(),HttpStatus.OK);
}


}
