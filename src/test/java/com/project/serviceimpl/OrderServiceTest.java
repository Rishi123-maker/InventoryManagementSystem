package com.project.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepo;

    @Mock
    private StockRepository stockRepo;

    @Mock
    private ProductRepository productRepo;

    @Mock
    private StockServiceImpl stockService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() throws Exception {
        Order order = new Order();
        Product product = new Product();
        product.setProductId(1);
        order.setProduct(product);
        order.setQuantity(5);

        Stock stock = new Stock();
        stock.setProductId(1);
        stock.setQuantity(20);

        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock));

        orderService.create(order);

        verify(orderRepo, times(1)).save(order);
        verify(productRepo, times(1)).save(product);
        verify(stockRepo, times(1)).save(stock);
        verify(stockService, times(1)).updateReorderLevel(1);
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        when(orderRepo.findByOrderId(1)).thenReturn(order);

        Order result = orderService.getOrderById(1);

        assertEquals(order, result);
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepo.findByOrderId(1)).thenReturn(null);

        assertThrows(IdNotFoundException.class, () -> orderService.getOrderById(1));
    }

    @Test
    void testGetOrderByProductName() {
        Order order = new Order();
        List<Optional<Order>> orders = Arrays.asList(Optional.of(order));
        when(orderRepo.findByProductName("Product1")).thenReturn(orders);

        List<Optional<Order>> result = orderService.getOrderByProductName("Product1");

        assertEquals(orders, result);
    }

    @Test
    void testGetOrderByProductNameNotFound() {
        when(orderRepo.findByProductName("Product1")).thenReturn(Arrays.asList());

        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderByProductName("Product1"));
    }

    @Test
    void testUpdateOrderStatus() {
        Order order = new Order();
        when(orderRepo.findByOrderId(1)).thenReturn(order);

        orderService.updateOrderStatus(1, "Shipped");

        assertEquals("Shipped", order.getStatus());
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    void testUpdateOrderStatusNotFound() {
        when(orderRepo.findByOrderId(1)).thenReturn(null);

        assertThrows(IdNotFoundException.class, () -> orderService.updateOrderStatus(1, "Shipped"));
    }

    @Test
    void testGetOrderByDate() {
        Order order = new Order();
        List<Order> orders = Arrays.asList(order);
        when(orderRepo.getOrderByDate(any(LocalDate.class))).thenReturn(orders);

        List<Order> result = orderService.getOrderByDate(LocalDate.now());

        assertEquals(orders, result);
    }

    @Test
    void testGetOrderByDateNotFound() {
        when(orderRepo.getOrderByDate(any(LocalDate.class))).thenReturn(Arrays.asList());

        assertThrows(InappropriateDateException.class, () -> orderService.getOrderByDate(LocalDate.now()));
    }

//    @Test
//    void testGetHighestOrderedProduct() {
//        Object[] product = new Object[] { "Product1", 10L };
//        List<Object[]> products = Arrays.asList(product);
//        Pageable pageable = PageRequest.of(0, 1);
//        when(orderRepo.findHighestOrderedProduct(pageable)).thenReturn(products);
//
//        List<Object[]> result = orderService.getHighestOrderedProduct();
//
//        assertEquals(products, result);
//    }

    @Test
    void testDeleteByOrderId() {
        Order order = new Order();
        when(orderRepo.findByOrderId(1)).thenReturn(order);

        String result = orderService.deleteByOrderId(1);

        assertEquals("Successfully deleted", result);
        verify(orderRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteByOrderIdNotFound() {
        when(orderRepo.findByOrderId(1)).thenReturn(null);

        assertThrows(IdNotFoundException.class, () -> orderService.deleteByOrderId(1));
    }

    @Test
    void testDeleteAll() {
        String result = orderService.deleteAll();

        assertEquals("Deleted All entries", result);
        verify(orderRepo, times(1)).deleteAll();
    }
}