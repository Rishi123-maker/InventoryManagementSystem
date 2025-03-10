package com.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/orders")
public class OrderController {
@Autowired
private OrderService orderService;
@GetMapping("/")
public ResponseEntity<String> Testing()
{
	return new ResponseEntity<>("Hi this is orders",HttpStatus.OK);
}
@GetMapping("/getById/{id}")
public ResponseEntity<Order> getOrderByID(@PathVariable int id)
{
 return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
}
@PostMapping("/create")
public ResponseEntity<String> create(@RequestBody Order o)
{
  orderService.create(o);
  return new ResponseEntity<>("Successfully created an entry",HttpStatus.OK);
}
@GetMapping("/getOrderByProductName/{name}")
public ResponseEntity<Optional<Order>> getOrderByProductName(@PathVariable String name)
{
 return new ResponseEntity<>(orderService.getOrderByProductName(name),HttpStatus.OK);
}
@GetMapping("/getOrderByStatus/{status}")
public ResponseEntity<Optional<Order>> getOrderByStatus(@PathVariable String status)
{
	return new ResponseEntity<>(orderService.getOrderByStatus(status),HttpStatus.OK);
}
@PutMapping("/updateOrderStatus")
public ResponseEntity<String> updateOrderStatus(@RequestParam int id, @RequestParam String status)
{
	orderService.updateOrderStatus(id,status);
	return new ResponseEntity<String>("Updation Successful",HttpStatus.OK);
}

}
