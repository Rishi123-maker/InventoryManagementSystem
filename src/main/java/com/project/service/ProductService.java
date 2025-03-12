package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public String createProductDetails(Product p) {
		// TODO Auto-generated method stub
		productRepository.save(p);
		return "Inserted";
	}

	public List<Product> getProductDetails() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public Product getByProductId(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}

	public Product getByProductName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	public String updateProductName(int id, String name) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		p.setName(name);
		productRepository.save(p);
		return "Updated Name successfully";
	}

	public String updateProductDescription(int id, String description) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		p.setDescription(description);
		productRepository.save(p);
		return "Updated Description successfully";
	}
	
	public String updateProductPrice(int id, double price) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		p.setPrice(price);
		productRepository.save(p);
		return "Updated Price successfully";
	}

	public String updateProductStockLevel(int id, String stockLevel) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		p.setStockLevel(stockLevel);
		productRepository.save(p);
		return "Updated Stock Level successfully";
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		return "Deleted successfully";
	}

	public String deleteAll() {
		// TODO Auto-generated method stub
		productRepository.deleteAll();
		return "Deleted All Rows";
	}


}
