package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.entity.Stock;

public interface StockService {
    String create(Stock stock);

    Optional<Stock> getStockById(int id);

    List<Stock> getStockByReorderLevel(String reorderLevel);

    List<Object[]> getHighestQuantity();

    String deleteById(int id);

    String deleteAll();

    List<Stock> findAll();

    String updateStockQuantity(int id, int quantity);
}