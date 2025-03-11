package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
public void create(Product product)
{
	productRepo.save(product);
}

}
