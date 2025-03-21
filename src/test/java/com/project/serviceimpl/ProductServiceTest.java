package com.project.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.entity.Product;
import com.project.entity.enums.StockLevel;
import com.project.exception.IdNotFoundException;
import com.project.repository.ProductRepository;
import com.project.service.ProductService;
import com.project.serviceimpl.ProductServiceImpl;
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() throws Exception {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.create(product);
        assertEquals("Inserted", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testCreate_Exception() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(null);

        assertThrows(Exception.class, () -> {
            productService.create(product);
        });
    }

    @Test
    public void testGetProductDetails() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getProductDetails();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetByProductId() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getByProductId(1);
        assertTrue(result.isPresent());
    }

    @Test
    public void testGetByProductId_NotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            productService.getByProductId(1);
        });
    }

    @Test
    public void testGetByProductName() {
        Product product = new Product();
        when(productRepository.findByName("ProductName")).thenReturn(product);

        Product result = productService.getByProductName("ProductName");
        assertNotNull(result);
    }

    @Test
    public void testUpdateProductName() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.updateProductName(1, "New Name");
        assertEquals("Updated Name successfully", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductName_NotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            productService.updateProductName(1, "New Name");
        });
    }

    @Test
    public void testUpdateProductDescription() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.updateProductDescription(1, "New Description");
        assertEquals("Updated Description successfully", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductPrice() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.updateProductPrice(1, 100.0);
        assertEquals("Updated Price successfully", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductStockLevel() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.updateProductStockLevel(1, StockLevel.moderate);
        assertEquals("Updated Stock Level successfully", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteById() {
        productService.deleteById(1);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAll() {
        productService.deleteAll();
        verify(productRepository, times(1)).deleteAll();
    }
}