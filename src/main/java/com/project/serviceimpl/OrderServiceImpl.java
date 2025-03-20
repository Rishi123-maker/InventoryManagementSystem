//package com.project.serviceimpl;
//
//import java.time.LocalDate;
//
//import java.util.List;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.entity.Order;
//import com.project.entity.Product;
//import com.project.entity.Stock;
//import com.project.exception.IdNotFoundException;
//import com.project.exception.InappropriateDateException;
//import com.project.exception.ResourceNotFoundException;
//import com.project.repository.OrderRepository;
//import com.project.repository.ProductRepository;
//import com.project.repository.StockRepository;
//
//import jakarta.transaction.Transactional;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//@Service
//public class OrderServiceImpl {
//	@Autowired
//private OrderRepository orderRepo;
//	@Autowired
//private StockRepository stockRepo;
//	@Autowired
//private ProductRepository productRepo;
//	public void create(Order order) {
//
//		orderRepo.save(order);
//		Product product=order.getProduct();
//	   Stock stock=stockRepo.findById(product.getProductId()).get();
//	   stock.setQuantity(stock.getQuantity()-order.getQuantity());
//	  product.setStockLevel(stock.getQuantity());
//	  productRepo.save(product);
//	   stockRepo.save(stock);
//	  
//		
//	}
//
//	public Order getOrderById(int id) {
//	   Order order=orderRepo.findByOrderId(id);
//	   if(order==null)
//	   {
//		   throw new IdNotFoundException("Id has not been found");
//	   }
//		return order;
//	}
//
//	public Optional<Order> getOrderByProductName(String name) {
//		Optional<Order> order=orderRepo.findByProductName(name);
//		if(order.isEmpty())
//		{
//			throw new ResourceNotFoundException("The required resource has not been found");
//		}
//		return order;
//	}
//
//	public Optional<Order> getOrderByStatus(String status) {
//		Optional<Order> order=orderRepo.findByStatus(status);
//		if(order.isEmpty())
//		{
//			throw new ResourceNotFoundException("The required resource has not been found");
//		}
//		return order;
//	
//	}
//
//
//
//	public void updateOrderStatus(int id, String status) {
//		Order o = orderRepo.findByOrderId(id);
//		if (o != null) {
//			o.setStatus(status);
//		}
//		else
//		{
//			throw new IdNotFoundException("Id has not been found");
//		}
//		orderRepo.save(o);
//	}
//
//
//	public List<Order> getOrderByDate(LocalDate startDate, LocalDate endDate) {
//		List<Order> orders= orderRepo.getOrderByDate(startDate, endDate);
//		if(orders.size()==0)
//		{
//			throw new InappropriateDateException("Enter correct date");
//		}
//		return orders;
//	}
//
//	public List<Object[]> getHighestOrderedProduct() {
//		Pageable pageable = PageRequest.of(0, 1);
//		return orderRepo.findHighestOrderedProduct(pageable);
//	}
//
//
//	public String deleteByOrderId(int id) {
//		 if(orderRepo.findByOrderId(id)==null)
//		 {
//			 throw new IdNotFoundException("Id has not been found");
//		 }
//		return "Successfully deleted";
//
//	}
//
//	public String deleteAll() {
//		
//		orderRepo.deleteAll();
//	
//		return "Deleted All entries";
//	}
//}




package com.project.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Order;
import com.project.entity.Product;
import com.project.entity.Stock;
import com.project.exception.IdNotFoundException;
import com.project.exception.InappropriateDateException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.OrderRepository;
import com.project.repository.ProductRepository;
import com.project.repository.StockRepository;
import com.project.service.OrderService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private StockRepository stockRepo;
    @Autowired
    private ProductRepository productRepo;

    @Override
    public void create(Order order) {
        orderRepo.save(order);
        Product product = order.getProduct();
        Stock stock = stockRepo.findById(product.getProductId()).get();
        stock.setQuantity(stock.getQuantity() - order.getQuantity());
        product.setStockLevel(stock.getQuantity());
        productRepo.save(product);
        stockRepo.save(stock);
    }

    @Override
    public Order getOrderById(int id) {
        Order order = orderRepo.findByOrderId(id);
        if (order == null) {
            throw new IdNotFoundException("Id has not been found");
        }
        return order;
    }

    @Override
    public Optional<Order> getOrderByProductName(String name) {
        Optional<Order> order = orderRepo.findByProductName(name);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("The required resource has not been found");
        }
        return order;
    }

    @Override
    public Optional<Order> getOrderByStatus(String status) {
        Optional<Order> order = orderRepo.findByStatus(status);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("The required resource has not been found");
        }
        return order;
    }

    @Override
    public void updateOrderStatus(int id, String status) {
        Order o = orderRepo.findByOrderId(id);
        if (o != null) {
            o.setStatus(status);
        } else {
            throw new IdNotFoundException("Id has not been found");
        }
        orderRepo.save(o);
    }

    @Override
    public List<Order> getOrderByDate(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepo.getOrderByDate(startDate, endDate);
        if (orders.size() == 0) {
            throw new InappropriateDateException("Enter correct date");
        }
        return orders;
    }

    @Override
    public List<Object[]> getHighestOrderedProduct() {
        Pageable pageable = PageRequest.of(0, 1);
        return orderRepo.findHighestOrderedProduct(pageable);
    }

    @Override
    public String deleteByOrderId(int id) {
        if (orderRepo.findByOrderId(id) == null) {
            throw new IdNotFoundException("Id has not been found");
        }
        return "Successfully deleted";
    }

    @Override
    public String deleteAll() {
        orderRepo.deleteAll();
        return "Deleted All entries";
    }
}