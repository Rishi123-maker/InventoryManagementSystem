package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByName(String name);
}
