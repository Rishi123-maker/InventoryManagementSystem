package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.entity.Product;
import com.project.entity.enums.StockLevel;

public interface ProductService {
	Product create(Product p) throws Exception;

	List<Product> getProductDetails();

	Optional<Product> getByProductId(int id);

	Product getByProductName(String name);

	String updateProductName(int id, String name);

	String updateProductDescription(int id, String description);

	String updateProductPrice(int id, double price);

	String deleteById(int id);
  List<Product> getAll();
	String deleteAll();
}