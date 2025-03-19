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
import com.project.entity.Report;
import com.project.exception.IdNotFoundException;
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
public ResponseEntity<?> getOrderByID(@PathVariable int id)
{
	try {
		Order order = orderService.getOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	catch(IdNotFoundException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	//return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
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
	//orderService.updateOrderStatus(id,status);
	return new ResponseEntity<String>("Updation Successful",HttpStatus.OK);
}

@GetMapping("/getOrderByDate")
public ResponseEntity<List<Order>> getOrderByDate(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate)
{
	return new ResponseEntity<>(orderService.getOrderByDate(startDate,endDate),HttpStatus.OK);
}

@GetMapping("/getHighestOrderedProduct")
public ResponseEntity<List<Object[]>>getHighestOrderedProduct()
{
	return new ResponseEntity<>(orderService.getHighestOrderedProduct(),HttpStatus.OK);
}

@DeleteMapping("/deleteByOrderId/{id}")
public ResponseEntity<String>deleteByOrderById(@PathVariable int id)
{    
	Order order = orderService.getOrderById(id).orElseThrow(() -> new IdNotFoundException("Order with ID " + id + " does not exist"));
    orderService.deleteByOrderId(id);
    return new ResponseEntity<>(id + " Deleted successfully", HttpStatus.OK);
	//return new ResponseEntity<>(orderService.deleteByOrderId(id),HttpStatus.OK);
}

@DeleteMapping("/deleteAll")
public ResponseEntity<String> deleteAll()
{
	return new ResponseEntity<>(orderService.deleteAll(),HttpStatus.OK);
}


}
