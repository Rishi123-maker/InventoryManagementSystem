//package com.project.serviceimpl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.project.entity.Stock;
//import com.project.exception.IdNotFoundException;
//import com.project.exception.ResourceNotFoundException;
//import com.project.repository.StockRepository;
//
//@Service
//public class StockServiceImpl {
//
//	@Autowired
//	private StockRepository stockRepo;
//
//	public String create(Stock stock) {
//		stockRepo.save(stock);
//		return "created";
//	}
//
//	public Optional<Stock> getStockById(int id) {
//		Optional<Stock>  stock=stockRepo.findById(id);
//		if(stock==null)
//		{
//			throw new IdNotFoundException("Id has not been found");
//		}
//		return stock;
//	}
//
//	public List<Stock> getStockByReorderLevel(String reorderLevel) {
//
//		return stockRepo.findByReorderLevel(reorderLevel);
//	}
//
//	public List<Object[]> getHighestQuantity() {
//		Pageable pageable = PageRequest.of(0, 1);
//		return stockRepo.findHighestQuantity(pageable);
//	}
//
//	public String deleteById(int id) {
//		Optional<Stock> stock=stockRepo.findById(id);
//		if(stock.isEmpty())
//		{
//			throw new IdNotFoundException("Id has not been found");
//		}
//		stockRepo.deleteById(id);
//		return "Deleted Successfully";
//	}
//
//	public String deleteAll() {
//		stockRepo.deleteAll();
//		return "Deleted all entries Successfully";
//	}
//
//	public List<Stock> findAll() {
//		return stockRepo.findAll();
//	}
//
//	public String updateStockQuantity(int id, int quantity) {
//		Optional<Stock> stockOpt = stockRepo.findById(id);
//		if (stockOpt.isPresent()) {
//			Stock stock = stockOpt.get();
//			stock.setQuantity(quantity);
//			stockRepo.save(stock);
//			return "Stock quantity updated successfully";
//		} else {
//			throw new ResourceNotFoundException("Resource Has not been Found");
//		}
//	}
//}



package com.project.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.entity.Stock;
import com.project.exception.IdNotFoundException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.StockRepository;
import com.project.service.StockService;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepo;

    @Override
    public String create(Stock stock) {
        stockRepo.save(stock);
        return "created";
    }

    @Override
    public Optional<Stock> getStockById(int id) {
        Optional<Stock> stock = stockRepo.findById(id);
        if (stock.isEmpty()) {
            throw new IdNotFoundException("Id has not been found");
        }
        return stock;
    }

    @Override
    public List<Stock> getStockByReorderLevel(String reorderLevel) {
        return stockRepo.findByReorderLevel(reorderLevel);
    }

    @Override
    public List<Object[]> getHighestQuantity() {
        Pageable pageable = PageRequest.of(0, 1);
        return stockRepo.findHighestQuantity(pageable);
    }

    @Override
    public String deleteById(int id) {
        Optional<Stock> stock = stockRepo.findById(id);
        if (stock.isEmpty()) {
            throw new IdNotFoundException("Id has not been found");
        }
        stockRepo.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public String deleteAll() {
        stockRepo.deleteAll();
        return "Deleted all entries Successfully";
    }

    @Override
    public List<Stock> findAll() {
        return stockRepo.findAll();
    }

    @Override
    public String updateStockQuantity(int id, int quantity) {
        Optional<Stock> stockOpt = stockRepo.findById(id);
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            stock.setQuantity(quantity);
            stockRepo.save(stock);
            return "Stock quantity updated successfully";
        } else {
            throw new ResourceNotFoundException("Resource Has not been Found");
        }
    }
}
