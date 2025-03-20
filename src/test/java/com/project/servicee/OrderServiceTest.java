
package com.project.servicee;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepo;

    @Mock
    private StockRepository stockRepo;

    @Mock
    private ProductRepository productRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        // Arrange
        Order order = new Order();
        Product product = new Product();
        product.setProductId(1);
        Stock stock = new Stock();
        stock.setQuantity(100);
        product.setStock(stock); // Assuming Product has a setStock method
        order.setProduct(product);
        order.setQuantity(10);

        when(stockRepo.findById(product.getProductId())).thenReturn(Optional.of(stock));

        // Act
        orderService.create(order);

        // Assert
        assertEquals(90, stock.getQuantity());
        assertEquals(90, product.getStockLevel());
        verify(orderRepo, times(1)).save(order);
        verify(stockRepo, times(1)).save(stock);
        verify(productRepo, times(1)).save(product);
    }

    @Test
    public void testGetOrderById() {
        // Arrange
        Order order = new Order();
        order.setOrderId(1);
        when(orderRepo.findByOrderId(1)).thenReturn(order);

        // Act
        Order result = orderService.getOrderById(1);

        // Assert
        assertEquals(order, result);
    }

    @Test
    public void testGetOrderByIdNotFound() {
        // Arrange
        when(orderRepo.findByOrderId(1)).thenReturn(null);

        // Act & Assert
        assertThrows(IdNotFoundException.class, () -> orderService.getOrderById(1));
    }

    @Test
    public void testGetOrderByProductName() {
        // Arrange
        Order order = new Order();
        when(orderRepo.findByProductName("Product1")).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = orderService.getOrderByProductName("Product1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    public void testGetOrderByProductNameNotFound() {
        // Arrange
        when(orderRepo.findByProductName("Product1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderByProductName("Product1"));
    }

    @Test
    public void testGetOrderByStatus() {
        // Arrange
        Order order = new Order();
        when(orderRepo.findByStatus("Pending")).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = orderService.getOrderByStatus("Pending");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    public void testGetOrderByStatusNotFound() {
        // Arrange
        when(orderRepo.findByStatus("Pending")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderByStatus("Pending"));
    }

    @Test
    public void testUpdateOrderStatus() {
        // Arrange
        Order order = new Order();
        order.setOrderId(1);
        when(orderRepo.findByOrderId(1)).thenReturn(order);

        // Act
        orderService.updateOrderStatus(1, "Shipped");

        // Assert
        assertEquals("Shipped", order.getStatus());
        verify(orderRepo, times(1)).save(order);
    }

    @Test
    public void testUpdateOrderStatusNotFound() {
        // Arrange
        when(orderRepo.findByOrderId(1)).thenReturn(null);

        // Act & Assert
        assertThrows(IdNotFoundException.class, () -> orderService.updateOrderStatus(1, "Shipped"));
    }

    @Test
    public void testGetOrderByDate() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        List<Order> orders = List.of(new Order());
        when(orderRepo.getOrderByDate(startDate, endDate)).thenReturn(orders);

        // Act
        List<Order> result = orderService.getOrderByDate(startDate, endDate);

        // Assert
        assertEquals(orders, result);
    }

    @Test
    public void testGetOrderByDateNotFound() {
        // Arrange
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        when(orderRepo.getOrderByDate(startDate, endDate)).thenReturn(List.of());

        // Act & Assert
        assertThrows(InappropriateDateException.class, () -> orderService.getOrderByDate(startDate, endDate));
    }

//    @Test
//    public void testGetHighestOrderedProduct() {
//        // Arrange
//        List<Object[]> highestOrderedProduct = List.of(new Object[] { "Product1", 100 });
//        Pageable pageable = PageRequest.of(0, 1);
//        when(orderRepo.findHighestOrderedProduct(pageable)).thenReturn(highestOrderedProduct);
//
//        // Act
//        List<Object[]> result = orderService.getHighestOrderedProduct();
//
//        // Assert
//        assertEquals(highestOrderedProduct, result);
//    }

    @Test
    public void testDeleteByOrderId() {
        // Arrange
        Order order = new Order();
        when(orderRepo.findByOrderId(1)).thenReturn(order);

        // Act
        String result = orderService.deleteByOrderId(1);

        // Assert
        assertEquals("Successfully deleted", result);
    }

    @Test
    public void testDeleteByOrderIdNotFound() {
        // Arrange
        when(orderRepo.findByOrderId(1)).thenReturn(null);

        // Act & Assert
        assertThrows(IdNotFoundException.class, () -> orderService.deleteByOrderId(1));
    }

    @Test
    public void testDeleteAll() {
        // Act
        String result = orderService.deleteAll();

        // Assert
        assertEquals("Deleted All entries", result);
        verify(orderRepo, times(1)).deleteAll();
    }

   
}
