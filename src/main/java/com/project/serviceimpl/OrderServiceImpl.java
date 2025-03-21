package com.project.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Order;
import com.project.entity.Product;
import com.project.entity.Stock;
import com.project.entity.enums.StockLevel;
import com.project.exception.IdNotFoundException;
import com.project.exception.InappropriateDateException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.OrderRepository;
import com.project.repository.ProductRepository;
import com.project.repository.StockRepository;
import com.project.service.OrderService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private StockRepository stockRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private StockServiceImpl stockService;

    @Override
    @Transactional
    public Order create(Order order) throws Exception {
        logger.log(Level.INFO, "Creating order: {0}", order);
        Product product = order.getProduct();
        Stock stock = stockRepo.findById(product.getProductId()).orElseThrow(() -> new Exception("Stock not found"));
        if (order.getQuantity() > stock.getQuantity()) {
            logger.log(Level.WARNING, "Order quantity exceeds stock quantity");
            throw new Exception("Order lesser Quantity");
        }

        Order newOrder=orderRepo.save(order);
        order.setProduct(productRepo.findById(product.getProductId()).orElseThrow(() -> new Exception("Product not found")));
        stock.setQuantity(stock.getQuantity() - order.getQuantity());
        if (stock.getQuantity() < 10) {
            product.setStockLevel(StockLevel.low);
        } else if (stock.getQuantity() < 30) {
            product.setStockLevel(StockLevel.moderate);
        } else {
            product.setStockLevel(StockLevel.high);
        }
        productRepo.save(product);
        stockRepo.save(stock);
        stockService.updateReorderLevel(stock.getProductId());
        logger.log(Level.INFO, "Order created successfully: {0}", order);
        return newOrder;
    }

    @Override
    public Order getOrderById(int id) {
        logger.log(Level.INFO, "Fetching order by ID: {0}", id);
        Order order = orderRepo.findByOrderId(id);
        if (order == null) {
            logger.log(Level.WARNING, "Order ID not found: {0}", id);
            throw new IdNotFoundException("Id has not been found");
        }
        return order;
    }

    @Override
    public List<Optional<Order>> getOrderByProductName(String name) {
        logger.log(Level.INFO, "Fetching orders by product name: {0}", name);
        List<Optional<Order>> orders = orderRepo.findByProductName(name);
        if (orders.isEmpty()) {
            logger.log(Level.WARNING, "Orders not found for product name: {0}", name);
            throw new ResourceNotFoundException("The required resource has not been found");
        }
        return orders;
    }

    @Override
    public Optional<Order> getOrderByStatus(String status) {
        logger.log(Level.INFO, "Fetching order by status: {0}", status);
        Optional<Order> order = orderRepo.findByStatus(status);
        if (order.isEmpty()) {
            logger.log(Level.WARNING, "Order not found for status: {0}", status);
            throw new ResourceNotFoundException("The required resource has not been found");
        }
        return order;
    }

    @Override
    public void updateOrderStatus(int id, String status) {
        logger.log(Level.INFO, "Updating order status for ID: {0} to status: {1}", new Object[]{id, status});
        Order o = orderRepo.findByOrderId(id);
        if (o != null) {
            o.setStatus(status);
        } else {
            logger.log(Level.WARNING, "Order ID not found: {0}", id);
            throw new IdNotFoundException("Id has not been found");
        }
        orderRepo.save(o);
    }

    @Override
    public List<Order> getOrderByDate(LocalDate date) {
        logger.log(Level.INFO, "Fetching orders by date: {0}", date);
        List<Order> orders = orderRepo.getOrderByDate(date);
        if (orders.size() == 0) {
            logger.log(Level.WARNING, "No orders found for date: {0}", date);
            throw new InappropriateDateException("Enter correct date");
        }
        return orders;
    }

    @Override
    public List<Object[]> getHighestOrderedProduct() {
        logger.log(Level.INFO, "Fetching highest ordered product");
        Pageable pageable = PageRequest.of(0, 1);
        return orderRepo.findHighestOrderedProduct(pageable);
    }

    @Override
    public String deleteByOrderId(int id) {
        logger.log(Level.INFO, "Deleting order by ID: {0}", id);
        if (orderRepo.findByOrderId(id) == null) {
            logger.log(Level.WARNING, "Order ID not found: {0}", id);
            throw new IdNotFoundException("Id has not been found");
        }
        orderRepo.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public String deleteAll() {
        logger.log(Level.INFO, "Deleting all orders");
        orderRepo.deleteAll();
        return "Deleted All entries";
    }
}