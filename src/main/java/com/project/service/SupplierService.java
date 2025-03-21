package com.project.service;

import java.util.List;

import com.project.entity.Supplier;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    String createSupplierDetails(Supplier s);

    Supplier getSupplierById(int id);

    String deleteSupplierById(int id);

    String updateSupplier(int id, String name);

    Supplier getSupplierByName(String name);

    String updateSupplierContactInfo(int id, String contactInfo);

    String updateSupplierProductsSupplied(int id, String productsSupplied);

    String deleteAllSuppliers();
}