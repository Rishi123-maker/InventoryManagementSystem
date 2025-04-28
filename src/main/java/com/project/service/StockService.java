package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.entity.Stock;

public interface StockService {
	

	Optional<Stock> getStockById(int id);

	List<Stock> getStockByReorderLevel(String reorderLevel);

	List<Object[]> getHighestQuantity();

	List<Stock> findAll();

	String updateStockQuantity(int id, int quantity);

	void updateReorderLevel(int id);
}