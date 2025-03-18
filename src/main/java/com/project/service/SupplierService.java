package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Supplier;
import com.project.exception.IdNotFoundException;
import com.project.repository.SupplierRepository;



@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;

	public List<Supplier> getAllSuppliers() {
		// TODO Auto-generated method stub
		return supplierRepository.findAll();
	}

	public String createSupplierDetails(Supplier s) {
		// TODO Auto-generated method stub
		supplierRepository.save(s);
		return "Inserted";
	}

	public Supplier getSupplierById(int id) {
		// TODO Auto-generated method stub
		return supplierRepository.findById(id).orElseThrow(()->new IdNotFoundException("Id has not been found"));
	}

	public String deleteSupplierById(int id) {
		// TODO Auto-generated method stub
		supplierRepository.deleteById(id);
		return "deleted";
	}

	public String updateSupplier(int id, String name) {
		// TODO Auto-generated method stub
		Supplier sup = supplierRepository.findById(id).get();
//		sup.setName(name);
		if(sup==null)
		{
			throw new IdNotFoundException("Id not found");
		}
		supplierRepository.save(sup);
		return "Updated";
		
	}

	public Supplier getSupplierByName(String name) {
		// TODO Auto-generated method stub
		return supplierRepository.findByName(name).orElse(null);
	}

	public String updateSupplierContactInfo(int id, String contactInfo) {
		// TODO Auto-generated method stub
		Supplier sup = supplierRepository.findById(id).get();
		if(sup==null)
		{
			throw new IdNotFoundException("Id not found");
		}
//		sup.setContactInfo(contactInfo);
		supplierRepository.save(sup);
		return "Updated";
	}

	public String updateSupplierProductsSupplied(int id, String productsSupplied) {
		// TODO Auto-generated method stub
		Supplier sup = supplierRepository.findById(id).get();
		if(sup==null)
		{
			throw new IdNotFoundException("Id not found");
		}
//		sup.setProductsSupplied(productsSupplied);
		supplierRepository.save(sup);
		return "Updated";
	}

	public String deleteAllSuppliers() {
	    supplierRepository.deleteAll();
	    return "Deleted";
	}
}
