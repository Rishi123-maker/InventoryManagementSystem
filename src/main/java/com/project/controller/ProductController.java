package com.project.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.entity.enums.StockLevel;
import com.project.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @CrossOrigin(origins="*")
    @PostMapping("/admin/create")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) throws Exception {
    	
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
    }
    //path for both user and admin
  @CrossOrigin(origins="*")
    @GetMapping("/admin/getById/{id}")
    public ResponseEntity<Optional<Product>> getByProductId(@PathVariable int id) {
        Optional<Product> product = productService.getByProductId(id);
        return product.isPresent() ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  @CrossOrigin(origins="*")
    @GetMapping("/admin/getByName/{name}")
    public ResponseEntity<Product> getByProductName(@Valid @PathVariable String name) {
        Product product = productService.getByProductName(name);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  @CrossOrigin(origins="*")
    @PutMapping("/admin/updateName/{id}/{name}")
    public ResponseEntity<String> updateProductName(@PathVariable int id, @Valid @PathVariable String name) {
        String result = productService.updateProductName(id, name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
  @CrossOrigin(origins="*")
    @PutMapping("/admin/updateDesc/{id}/{description}")
    public ResponseEntity<String> updateProductDescription(@PathVariable int id, @Valid @PathVariable String description) {
        String result = productService.updateProductDescription(id, description);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
  @CrossOrigin(origins="*")
    @PutMapping("/admin/updatePrice/{id}/{price}")
    public ResponseEntity<String> updateProductPrice(@PathVariable int id, @Valid @PathVariable double price) {
        String result = productService.updateProductPrice(id, price);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
  @CrossOrigin(origins="*")
    @DeleteMapping("/admin/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        String result = productService.deleteById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
  @CrossOrigin(origins="*")
    @DeleteMapping("/admin/deleteAll")
    public ResponseEntity<String> deleteAll() {
        String result = productService.deleteAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
  
    @CrossOrigin(origins="*")
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>>getAll()
    {
    	return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }
}