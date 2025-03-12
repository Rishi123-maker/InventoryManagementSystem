package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Supplier;
import com.project.service.SupplierService;



@RestController
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/getAll")
	public List<Supplier> getSupplier(Supplier s){
		return supplierService.getAllSuppliers();
	}
	
	@PostMapping("/create")
	public String create(@RequestBody Supplier s) {
		return supplierService.createSupplierDetails(s);
	}
	
	@GetMapping("/getById/{id}")
	public Supplier getSupplierById(@PathVariable int id) {
		return supplierService.getSupplierById(id);
	}
	@GetMapping("getByName/{name}")
	public Supplier getSupplierByName(@PathVariable String name) {
		return supplierService.getSupplierByName(name);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteSupplierById(@PathVariable int id) {
		return supplierService.deleteSupplierById(id);
	}
	
	@PutMapping("/updateName/{id}/{name}")
	public String updateSupplierById(@PathVariable int id, @PathVariable String name) {
		return supplierService.updateSupplier(id, name);
	}
	
//	@PutMapping("/updateContactInfo/{id}")
//	public String updateSupplierContactInfo(@PathVariable int id, @RequestBody String contactInfo) {
//		return supplierService.updateSupplierContactInfo(id, contactInfo);
//
//	}
	@PutMapping("/updateContactInfo/{id}/{contactInfo}")
	public String updateSupplierContactInfo(@PathVariable int id, @PathVariable String contactInfo) {
		return supplierService.updateSupplierContactInfo(id, contactInfo);

	}
	@PutMapping("/updateProductsSupplied/{id}/{productsSupplied}")
	public String updateSupplierProductsSupplied(@PathVariable int id, @PathVariable String productsSupplied) {
		return supplierService.updateSupplierProductsSupplied(id, productsSupplied);

	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAllSuppliers() {
	    return supplierService.deleteAllSuppliers();
//	    return "All suppliers have been deleted successfully.";
	}
	
}
