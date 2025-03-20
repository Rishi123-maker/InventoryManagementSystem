package com.project.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.entity.Stock;
import com.project.serviceimpl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductServiceImpl productService;
	@GetMapping("/")
	public ResponseEntity<String> Testing()
	{
		return new ResponseEntity<String>("Hi this is products",HttpStatus.OK);
	}
	@PostMapping("/admin/create")
public String create(@RequestBody Product product) throws Exception
{  
		Product p=new Product();
		Stock s=new Stock();
	return productService.create(product);
}
	@GetMapping("/admin/getById/{id}")
	public Optional<Product> getByProductId(@PathVariable int id) {
		return productService.getByProductId(id);
	}
	@GetMapping("/admin/getByName/{name}")
	public Product getByProductName(@PathVariable String name) {
		return productService.getByProductName(name);
	}
	@PutMapping("/admin/updateName/{id}/{name}")
	public String updateProductName(@PathVariable int id, @PathVariable String name) {
		return productService.updateProductName(id, name);
	}
	
	@PutMapping("/admin/updateDesc/{id}/{description}")
	public String updateProductDescription(@PathVariable int id, @PathVariable String description) {
		return productService.updateProductDescription(id, description);
	}
	
	@PutMapping("/admin/updatePrice/{id}/{price}")
	public String updateProductPrice(@PathVariable int id, @PathVariable double price) {
		return productService.updateProductPrice(id, price);
	}
	
	@PutMapping("/admin/updateLevel/{id}/{stockLevel}")
	public String updateProductStockLevel(@PathVariable int id, @PathVariable int stockLevel) {
		return productService.updateProductStockLevel(id, stockLevel);
	}
	
	@DeleteMapping("/admin/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		return productService.deleteById(id);
	}
	
	@DeleteMapping("/admin/deleteAll")
	public String deleteAll() {
		return productService.deleteAll();
	}

}
