package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.entity.Product;
import com.project.exception.IdNotFoundException;
import com.project.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


	public String create(Product p) throws Exception {
		// TODO Auto-generated method stub
		 Product product=productRepository.save(p);
		 if(product==null)
		 {
			 throw new Exception("Unable to create");
		 }
		return "Inserted";
	}

	public List<Product> getProductDetails() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public Optional<Product> getByProductId(int id) {
		// TODO Auto-generated method stub
		Optional<Product> product= productRepository.findById(id);
		if(product.isEmpty())
		{
			throw new IdNotFoundException("Id has Not been Found");
		}
		return product;
	}

	public Product getByProductName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	public String updateProductName(int id, String name) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		if(p==null)
		{
			throw new IdNotFoundException("Id has Not been Found");
		}
		p.setName(name);
		productRepository.save(p);
		return "Updated Name successfully";
	}

	public String updateProductDescription(int id, String description) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		if(p==null)
		{
			throw new IdNotFoundException("Id has Not been Found");
		}
		p.setDesc(description);
		productRepository.save(p);
		return "Updated Description successfully";
	}
	
	public String updateProductPrice(int id, double price) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		if(p==null)
		{
			throw new IdNotFoundException("Id has Not been Found");
		}
		p.setPrice(price);
		productRepository.save(p);
		return "Updated Price successfully";
	}

	public String updateProductStockLevel(int id, int stockLevel) {
		// TODO Auto-generated method stub
		Product p = productRepository.findById(id).get();
		if(p==null)
		{
			throw new IdNotFoundException("Id has Not been Found");
		}
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
