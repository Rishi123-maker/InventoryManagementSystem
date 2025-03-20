package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Stock;
import com.project.serviceimpl.StockServiceImpl;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockServiceImpl stockService;

	@GetMapping("/")
	public ResponseEntity<String> Testing() {
		return new ResponseEntity<>("Hi this is stocks", HttpStatus.OK);
	}

	@GetMapping("/admin/getById/{id}")
	public ResponseEntity<Optional<Stock>> getStockByID(@PathVariable int id) {
		return new ResponseEntity<>(stockService.getStockById(id), HttpStatus.OK);
	}

	@PostMapping("/admin/create")
	public ResponseEntity<String> create(@RequestBody Stock s) {
		stockService.create(s);
		return new ResponseEntity<>("Successfully created an entry", HttpStatus.OK);
	}

	@GetMapping("/admin/getStockByReorderLevel/{reorderLevel}")
	public ResponseEntity<List<Stock>> getStockByReorderLevel(@PathVariable String reorderLevel) {

		stockService.getStockByReorderLevel(reorderLevel);
		return new ResponseEntity<>(stockService.getStockByReorderLevel(reorderLevel), HttpStatus.OK);
	}

	@GetMapping("/admin/getHighestQuantity")
	public ResponseEntity<List<Object[]>> getHighestQuantity() {
		return new ResponseEntity<>(stockService.getHighestQuantity(), HttpStatus.OK);
	}

	@DeleteMapping("/admin/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		return new ResponseEntity<String>(stockService.deleteById(id), HttpStatus.OK);
	}

	@DeleteMapping("/admin/deleteAll")
	public ResponseEntity<String> deleteAll() {
		return new ResponseEntity<String>(stockService.deleteAll(), HttpStatus.OK);
	}

	@GetMapping("/admin/findAll")
	public ResponseEntity<List<Stock>> findAll() {
		return new ResponseEntity<List<Stock>>(stockService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/admin/updateStockQuantity/{id}/{quantity}")
	public ResponseEntity<String> updateStockQuantity(@PathVariable int id, @PathVariable int quantity) {
		String response = stockService.updateStockQuantity(id, quantity);
		if (response.equals("Stock quantity updated successfully")) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

}
