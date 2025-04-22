package com.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Supplier;
import com.project.serviceimpl.SupplierServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	private SupplierServiceImpl supplierService;

	@CrossOrigin(origins="*")
	@GetMapping("/admin/getAll")
	public ResponseEntity<List<Supplier>> getSupplier(Supplier s) {
		return new ResponseEntity<>(supplierService.getAllSuppliers(),HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@PostMapping("/admin/create")
	public ResponseEntity<Supplier> create(@RequestBody Supplier s) {
		return new ResponseEntity<>(supplierService.createSupplierDetails(s),HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@GetMapping("/admin/getById/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable int id) {
		return new ResponseEntity<>(supplierService.getSupplierById(id),HttpStatus.OK);
	}
     
	@GetMapping("/admin/getByName/{name}")
	public ResponseEntity<Supplier> getSupplierByName(@Valid @PathVariable String name) {
		return new  ResponseEntity<>(supplierService.getSupplierByName(name),HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<String> deleteSupplierById(@PathVariable int id) {
		return new ResponseEntity<>(supplierService.deleteSupplierById(id),HttpStatus.OK);
	}

	@PutMapping("/admin/updateName/{id}/{name}")
	public ResponseEntity<String> updateSupplierById(@PathVariable int id, @Valid @PathVariable String name) {
		return new ResponseEntity<>(supplierService.updateSupplier(id, name),HttpStatus.OK);
	}

	@PutMapping("/admin/updateContactInfo/{id}/{contactInfo}")
	public ResponseEntity<String> updateSupplierContactInfo(@PathVariable int id, @PathVariable String contactInfo) {
		return new ResponseEntity<>(supplierService.updateSupplierContactInfo(id, contactInfo),HttpStatus.OK);

	}
	@CrossOrigin(origins="*")
	@PutMapping("/admin/update")
	public ResponseEntity<Supplier>update(@RequestBody Supplier s)
	{ 
	return new ResponseEntity<>(supplierService.update(s),HttpStatus.OK);}

	@PutMapping("/admin/updateProductsSupplied/{id}/{productsSupplied}")
	public ResponseEntity<String> updateSupplierProductsSupplied(@PathVariable int id, @Valid @PathVariable String productsSupplied) {
		return new ResponseEntity<>(supplierService.updateSupplierProductsSupplied(id, productsSupplied),HttpStatus.OK);

	}

	@DeleteMapping("/admin/deleteAll")
	public ResponseEntity<String> deleteAllSuppliers() {
		return new ResponseEntity<>(supplierService.deleteAllSuppliers(),HttpStatus.OK);

	}

}
