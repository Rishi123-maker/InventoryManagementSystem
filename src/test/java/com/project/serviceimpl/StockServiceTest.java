package com.project.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.entity.Stock;
import com.project.exception.IdNotFoundException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.StockRepository;

public class StockServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    {
        Stock stock = new Stock();
        when(stockRepo.save(stock)).thenReturn(stock);
        String result = stockService.create(stock);
        assertEquals("created", result);
        verify(stockRepo, times(1)).save(stock);
    }
    }

    @Test
    public void testGetStockById() {
        Stock stock = new Stock();
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock));
        Optional<Stock> result = stockService.getStockById(1);
        assertTrue(result.isPresent());
        assertEquals(stock, result.get());
    }

    @Test
    public void testGetStockByIdNotFound() {
        when(stockRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> stockService.getStockById(1));
    }

    @Test
    public void testGetStockByReorderLevel() {
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock());
        when(stockRepo.findByReorderLevel("low")).thenReturn(stocks);
        List<Stock> result = stockService.getStockByReorderLevel("low");
        assertEquals(2, result.size());
    }

//    @Test
//    public void testGetHighestQuantity() {
//        List<Object[]> stocks = Arrays.asList(new Object[]{1, 100});
//        when(stockRepo.findHighestQuantity(any())).thenReturn(stocks);
//        List<Object[]> result = stockService.getHighestQuantity();
//        assertEquals(1, result.size());
//    }

    @Test
    public void testDeleteById() {
        Stock stock = new Stock();
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock));
        String result = stockService.deleteById(1);
        assertEquals("Deleted Successfully", result);
        verify(stockRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(stockRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> stockService.deleteById(1));
    }

    @Test
    public void testDeleteAll() {
        String result = stockService.deleteAll();
        assertEquals("Deleted all entries Successfully", result);
        verify(stockRepo, times(1)).deleteAll();
    }

    @Test
    public void testFindAll() {
        List<Stock> stocks = Arrays.asList(new Stock(), new Stock());
        when(stockRepo.findAll()).thenReturn(stocks);
        List<Stock> result = stockService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateStockQuantity() {
        Stock stock = new Stock();
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock));
        String result = stockService.updateStockQuantity(1, 50);
        assertEquals("Stock quantity updated successfully", result);
        verify(stockRepo, times(1)).save(stock);
    }

    @Test
    public void testUpdateStockQuantityNotFound() {
        when(stockRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> stockService.updateStockQuantity(1, 50));
    }

    @Test
    public void testUpdateReorderLevel() {
        Stock stock = new Stock();
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock));
        when(stockRepo.getOrderQuantity(1)).thenReturn(6);
        stockService.updateReorderLevel(1);
        verify(stockRepo, times(1)).save(stock);
    }
}