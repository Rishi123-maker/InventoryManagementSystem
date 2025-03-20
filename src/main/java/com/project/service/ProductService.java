package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.entity.Product;

public interface ProductService {
    String create(Product p) throws Exception;

    List<Product> getProductDetails();

    Optional<Product> getByProductId(int id);

    Product getByProductName(String name);

    String updateProductName(int id, String name);

    String updateProductDescription(int id, String description);

    String updateProductPrice(int id, double price);

    String updateProductStockLevel(int id, int stockLevel);

    String deleteById(int id);

    String deleteAll();
}