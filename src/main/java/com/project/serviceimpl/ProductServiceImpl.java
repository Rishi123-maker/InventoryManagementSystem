package com.project.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.entity.enums.StockLevel;
import com.project.exception.IdNotFoundException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.ProductRepository;
import com.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product create(Product p) throws Exception {
		logger.log(Level.INFO, "Creating product: {0}", p);
		if (p.getStock().getQuantity() > 50) {
			p.setStockLevel(StockLevel.high);
		} else if (p.getStock().getQuantity() > 10 && p.getStock().getQuantity() < 50) {
			p.setStockLevel(StockLevel.moderate);
		} else {
			p.setStockLevel(StockLevel.low);
		}
		p.getStock().setReorderLevel("low");
		Product product = productRepository.save(p);
		if (product == null) {
			logger.log(Level.WARNING, "Unable to create product");
			throw new Exception("Unable to create");
		}
		logger.log(Level.INFO, "Product created successfully: {0}", product);
		return product;
	}

	@Override
	public List<Product> getProductDetails() {
		logger.log(Level.INFO, "Fetching all product details");
		List<Product> productList= productRepository.findAll();
		if(productList.size()==0)
		{
			throw new ResourceNotFoundException("Resource has not been found");
		}
		return productList;
	}

	@Override
	public Optional<Product> getByProductId(int id) {
		logger.log(Level.INFO, "Fetching product by ID: {0}", id);
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			logger.log(Level.WARNING, "Product ID not found: {0}", id);
			throw new IdNotFoundException("Id has Not been Found");
		}
		return product;
	}

	@Override
	public Product getByProductName(String name) {
		logger.log(Level.INFO, "Fetching product by name: {0}", name);
		Product p=productRepository.findByName(name);
		if(p==null)
		{
			throw new ResourceNotFoundException("Resource has not been found");
		}
		return p;
	}

	@Override
	public String updateProductName(int id, String name) {
		logger.log(Level.INFO, "Updating product name for ID: {0} to name: {1}", new Object[] { id, name });
		Optional<Product> p = productRepository.findById(id);
		if (p.isEmpty()) {
			logger.log(Level.WARNING, "Product ID not found: {0}", id);
			throw new IdNotFoundException("Id has Not been Found");
		}
		p.get().setName(name);
		productRepository.save(p.get());
		logger.log(Level.INFO, "Product name updated successfully for ID: {0}", id);
		return "Updated Name successfully";
	}

	@Override
	public String updateProductDescription(int id, String description) {
		logger.log(Level.INFO, "Updating product description for ID: {0}", id);
		Product p = productRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id has Not been Found"));
		p.setDesc(description);
		productRepository.save(p);
		logger.log(Level.INFO, "Product description updated successfully for ID: {0}", id);
		return "Updated Description successfully";
	}

	@Override
	public String updateProductPrice(int id, double price) {
		logger.log(Level.INFO, "Updating product price for ID: {0}", id);
		Product p = productRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id has Not been Found"));
		p.setPrice(price);
		productRepository.save(p);
		logger.log(Level.INFO, "Product price updated successfully for ID: {0}", id);
		return "Updated Price successfully";
	}

	

	@Override
	public String deleteById(int id) {
		logger.log(Level.INFO, "Deleting product by ID: {0}", id);
		productRepository.deleteById(id);
		logger.log(Level.INFO, "Product deleted successfully for ID: {0}", id);
		return "Deleted successfully";
	}

	@Override
	public String deleteAll() {
		logger.log(Level.INFO, "Deleting all products");
		productRepository.deleteAll();
		logger.log(Level.INFO, "All products deleted successfully");
		return "Deleted All Rows";
	}

	@Override
	public List<Product> getAll() {
		
		return productRepository.findAll();
	}
}