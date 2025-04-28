package com.project.serviceimpl;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.project.entity.Product;
import com.project.entity.Stock;
import com.project.entity.enums.StockLevel;
import com.project.exception.IdNotFoundException;
import com.project.repository.ProductRepository;
 
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
    public void testCreate_Success() throws Exception {
        Product product = new Product();
        Stock stock = new Stock();
        stock.setQuantity(20);
        product.setStock(stock);
 
        when(productRepository.save(product)).thenReturn(product);
 
        Product result = productService.create(product);
 
        assertEquals(product, result);
        assertEquals(StockLevel.moderate, product.getStockLevel());
        verify(productRepository).save(product);
    }
 
    @Test
    public void testCreate_UnableToCreate() {
        Product product = new Product();
        Stock stock = new Stock();
        stock.setQuantity(20);
        product.setStock(stock);
 
        when(productRepository.save(product)).thenReturn(null);
 
        Exception exception = assertThrows(Exception.class, () -> productService.create(product));
        assertEquals("Unable to create", exception.getMessage());
    }
 
    @Test
    public void testGetProductDetails() {
        List<Product> products = List.of(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);
 
        List<Product> result = productService.getProductDetails();
 
        assertEquals(products, result);
    }
 
    @Test
    public void testGetByProductId_Success() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
 
        Optional<Product> result = productService.getByProductId(1);
 
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }
 
    @Test
    public void testGetByProductId_IdNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(IdNotFoundException.class, () -> productService.getByProductId(1));
    }
 
    @Test
    public void testGetByProductName() {
        Product product = new Product();
        when(productRepository.findByName("Product")).thenReturn(product);
 
        Product result = productService.getByProductName("Product");
 
        assertEquals(product, result);
    }
 
    @Test
    public void testUpdateProductName_Success() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
 
        String result = productService.updateProductName(1, "New Name");
 
        assertEquals("Updated Name successfully", result);
        verify(productRepository).save(product);
        assertEquals("New Name", product.getName());
    }
 
    @Test
    public void testUpdateProductName_IdNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(IdNotFoundException.class, () -> productService.updateProductName(1, "New Name"));
    }
 
    @Test
    public void testUpdateProductDescription_Success() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
 
        String result = productService.updateProductDescription(1, "New Description");
 
        assertEquals("Updated Description successfully", result);
        verify(productRepository).save(product);
        assertEquals("New Description", product.getDesc());
    }
 
    @Test
    public void testUpdateProductDescription_IdNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(IdNotFoundException.class, () -> productService.updateProductDescription(1, "New Description"));
    }
 
    @Test
    public void testUpdateProductPrice_Success() {
        Product product = new Product();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
 
        String result = productService.updateProductPrice(1, 100.0);
 
        assertEquals("Updated Price successfully", result);
        verify(productRepository).save(product);
        assertEquals(100.0, product.getPrice());
    }
 
    @Test
    public void testUpdateProductPrice_IdNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(IdNotFoundException.class, () -> productService.updateProductPrice(1, 100.0));
    }
 
    @Test
    public void testDeleteById_Success() {
        String result = productService.deleteById(1);
 
        assertEquals("Deleted successfully", result);
        verify(productRepository).deleteById(1);
    }
 
    @Test
    public void testDeleteAll() {
        String result = productService.deleteAll();
 
        assertEquals("Deleted All Rows", result);
        verify(productRepository).deleteAll();
    }
}