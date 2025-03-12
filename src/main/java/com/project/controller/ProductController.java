package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getAll")
	public List<Product> getProducts(Product p){
		return productService.getProductDetails();
	}
	@PostMapping("/create")
	public String create(@RequestBody Product p) { 
		return productService.createProductDetails(p);
	}
	
	@GetMapping("/getById/{id}")
	public Product getByProductId(@PathVariable int id) {
		return productService.getByProductId(id);
	}
	@GetMapping("/getByName/{name}")
	public Product getByProductName(@PathVariable String name) {
		return productService.getByProductName(name);
	}
	@PutMapping("/updateName/{id}/{name}")
	public String updateProductName(@PathVariable int id, @PathVariable String name) {
		return productService.updateProductName(id, name);
	}
	
	@PutMapping("/updateDesc/{id}/{description}")
	public String updateProductDescription(@PathVariable int id, @PathVariable String description) {
		return productService.updateProductDescription(id, description);
	}
	
	@PutMapping("/updatePrice/{id}/{price}")
	public String updateProductPrice(@PathVariable int id, @PathVariable double price) {
		return productService.updateProductPrice(id, price);
	}
	
	@PutMapping("/updateLevel/{id}/{stockLevel}")
	public String updateProductStockLevel(@PathVariable int id, @PathVariable String stockLevel) {
		return productService.updateProductStockLevel(id, stockLevel);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		return productService.deleteById(id);
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		return productService.deleteAll();
	}
	
	

}
