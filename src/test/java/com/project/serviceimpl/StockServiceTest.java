package com.project.serviceimpl;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.project.entity.Stock;
import com.project.exception.IdNotFoundException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.StockRepository;
 
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {
 
    @Mock
    private StockRepository stockRepo;
 
    @InjectMocks
    private StockServiceImpl stockService;
 
    private Stock stock;
 
    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setProductId(1);
        stock.setQuantity(50);
        stock.setReorderLevel("low");
    }
 
    @Test
    void testGetStockById_WhenStockExists() {
        when(stockRepo.findById(1)).thenReturn(Optional.of(stock));
        Optional<Stock> result = stockService.getStockById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getProductId());
    }
 
    @Test
    void testGetStockById_WhenStockNotExists() {
        when(stockRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> stockService.getStockById(1));
    }
 
    @Test
    void testGetStockByReorderLevel() {
        when(stockRepo.findByReorderLevel("low")).thenReturn(Arrays.asList(stock));
        List<Stock> stocks = stockService.getStockByReorderLevel("low");
        assertEquals(1, stocks.size());
    }
 
    
 
    @Test
    void testUpdateStockQuantity_WhenStockNotExists() {
        when(stockRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> stockService.updateStockQuantity(1, 100));
    }
}