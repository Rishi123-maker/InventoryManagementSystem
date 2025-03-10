package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@GetMapping("/")
	public ResponseEntity<String> Testing()
	{
		return new ResponseEntity<String>("Hi this is products",HttpStatus.OK);
	}
	@PostMapping("/create")
public void create(@RequestBody Product product)
{
	productService.create(product);
}

}
