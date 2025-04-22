package com.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")

public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@CrossOrigin(origins="*")
	@GetMapping("/getById/{id}")
	public ResponseEntity<Order> getOrderByID(@PathVariable int id)

	{

		return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/getByCustomerId/{id}")
	public ResponseEntity<List<Order>> getByCustomerId(@PathVariable int id)
	{
		return new ResponseEntity<>(orderService.getByCustomerId(id),HttpStatus.OK);
	}
  @CrossOrigin(origins="*")
	@GetMapping("/admin/getAll")
	public ResponseEntity<List<Order>> getAll()
	{
		return new ResponseEntity<>(orderService.getAll(),HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@PostMapping("/customer/create")
	public ResponseEntity<Order> create(@RequestBody Order o) throws Exception {
		// orderService.create(o);
		return new ResponseEntity<Order>(orderService.create(o), HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@GetMapping("/admin/getOrderByProductName/{name}")
	public ResponseEntity<List<Optional<Order>>> getOrderByProductName(@PathVariable String name) {
		return new ResponseEntity<>(orderService.getOrderByProductName(name), HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@GetMapping("/admin/getOrderByStatus/{status}")

	public ResponseEntity<Optional<Order>> getOrderByStatus(@Valid @PathVariable String status) {
		return new ResponseEntity<>(orderService.getOrderByStatus(status), HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@PutMapping("/admin/updateOrderStatus")

	public ResponseEntity<String> updateOrderStatus(@Valid @RequestParam int id, @Valid @RequestParam String status) {
		orderService.updateOrderStatus(id, status);
		return new ResponseEntity<String>("Updation Successful", HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@GetMapping("/admin/getOrderByDate")

	public ResponseEntity<List<Order>> getOrderByDate(@Valid @RequestParam LocalDate date) {
		return new ResponseEntity<>(orderService.getOrderByDate(date), HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@GetMapping("/admin/getHighestOrderedProduct")

	public ResponseEntity<List<Object[]>> getHighestOrderedProduct() {
		return new ResponseEntity<>(orderService.getHighestOrderedProduct(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins="*")
	@DeleteMapping("/customer/deleteByOrderId/{id}")
	public ResponseEntity<String> deleteByOrderById(@Valid @PathVariable int id) {
		return new ResponseEntity<>(orderService.deleteByOrderId(id), HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@DeleteMapping("/admin/deleteAll")
	public ResponseEntity<String> deleteAll() {
		return new ResponseEntity<>(orderService.deleteAll(), HttpStatus.OK);
	}
    
}
