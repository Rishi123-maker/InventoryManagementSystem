package com.project.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.project.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.entity.Stock;
import com.project.entity.enums.StockLevel;
import com.project.exception.IdNotFoundException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.ProductRepository;
import com.project.repository.StockRepository;
import com.project.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	private static final Logger logger = Logger.getLogger(StockServiceImpl.class.getName());

	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private ProductRepository productRepo;
	@Override
	public Optional<Stock> getStockById(int id) {
		logger.log(Level.INFO, "Fetching stock by ID: {0}", id);
		Optional<Stock> stock = stockRepo.findById(id);
		if (stock.isEmpty()) {
			logger.log(Level.WARNING, "Stock ID not found: {0}", id);
			throw new IdNotFoundException("Id has not been found");
		}
		return stock;
	}

	@Override
	public List<Stock> getStockByReorderLevel(String reorderLevel) {
		logger.log(Level.INFO, "Fetching stocks by reorder level: {0}", reorderLevel);
		List<Stock> s=stockRepo.findByReorderLevel(reorderLevel);
		if(s.size()==0)
		{
			throw new ResourceNotFoundException("Resource has not been found");
		}
		return s;
	}

	@Override
	public List<Object[]> getHighestQuantity() {
		
		logger.log(Level.INFO, "Fetching stock with highest quantity");
		Pageable pageable = PageRequest.of(0, 3);
		return stockRepo.findHighestQuantity(pageable);
	}





	@Override
	public List<Stock> findAll() {
		logger.log(Level.INFO, "Fetching all stocks");
		return stockRepo.findAll();
	}

	@Override
	public String updateStockQuantity(int id, int quantity) {
		logger.log(Level.INFO, "Updating stock quantity for ID: {0} to quantity: {1}", new Object[] { id, quantity });
		Optional<Stock> stockOpt = stockRepo.findById(id);
		if (stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			stock.setQuantity(quantity);
			Product p=productRepo.findById(id).get();
			if (quantity < 10) {
				p.setStockLevel(StockLevel.low);
			} else if (quantity < 30) {
				p.setStockLevel(StockLevel.moderate);
			} else {
				p.setStockLevel(StockLevel.high);
			}
			productRepo.save(p);
			stockRepo.save(stock);
			logger.log(Level.INFO, "Stock quantity updated successfully for ID: {0}", id);
			return "Stock quantity updated successfully";
		} else {
			logger.log(Level.WARNING, "Stock ID not found: {0}", id);
			throw new ResourceNotFoundException("Resource Has not been Found");
		}
	}

	@Override
	public void updateReorderLevel(int id) {
		logger.log(Level.INFO, "Updating reorder level for stock ID: {0}", id);
		int orderQuantity = stockRepo.getOrderQuantity(id);
		logger.log(Level.INFO, "Order quantity for stock ID {0}: {1}", new Object[] { id, orderQuantity });
		if (orderQuantity > 5) {
			Stock stock = stockRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id has not been found"));
			stock.setReorderLevel("moderate");
			stockRepo.save(stock);
			logger.log(Level.INFO, "Reorder level updated to high for stock ID: {0}", id);
		} else if (orderQuantity > 10) {
			Stock stock = stockRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id has not been found"));
			stock.setReorderLevel("high");
			stockRepo.save(stock);
			logger.log(Level.INFO, "Reorder level updated to high for stock ID: {0}", id);
		}

	}
}