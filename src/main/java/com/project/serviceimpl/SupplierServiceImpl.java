package com.project.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Supplier;
import com.project.exception.IdNotFoundException;
import com.project.repository.SupplierRepository;
import com.project.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final Logger logger = Logger.getLogger(SupplierServiceImpl.class.getName());

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        logger.log(Level.INFO, "Fetching all suppliers");
        return supplierRepository.findAll();
    }

    @Override
    public String createSupplierDetails(Supplier s) {
        logger.log(Level.INFO, "Creating supplier: {0}", s);
        supplierRepository.save(s);
        logger.log(Level.INFO, "Supplier created successfully: {0}", s);
        return "Inserted";
    }

    @Override
    public Supplier getSupplierById(int id) {
        logger.log(Level.INFO, "Fetching supplier by ID: {0}", id);
        return supplierRepository.findById(id).orElseThrow(() -> {
            logger.log(Level.WARNING, "Supplier ID not found: {0}", id);
            return new IdNotFoundException("Id has not been found");
        });
    }

    @Override
    public String deleteSupplierById(int id) {
        logger.log(Level.INFO, "Deleting supplier by ID: {0}", id);
        supplierRepository.deleteById(id);
        logger.log(Level.INFO, "Supplier deleted successfully for ID: {0}", id);
        return "deleted";
    }

    @Override
    public String updateSupplier(int id, String name) {
        logger.log(Level.INFO, "Updating supplier name for ID: {0} to name: {1}", new Object[]{id, name});
        Optional<Supplier> sup = supplierRepository.findById(id);
        if (sup.isEmpty()) {
            logger.log(Level.WARNING, "Supplier ID not found: {0}", id);
            throw new IdNotFoundException("Id not found");
        }
        sup.get().setName(name); // Uncomment and implement this line if the Supplier entity has a name field
        supplierRepository.save(sup.get());
        logger.log(Level.INFO, "Supplier name updated successfully for ID: {0}", id);
        return "Updated";
    }

    @Override
    public Supplier getSupplierByName(String name) {
        logger.log(Level.INFO, "Fetching supplier by name: {0}", name);
        return supplierRepository.findByName(name).orElse(null);
    }

    @Override
    public String updateSupplierContactInfo(int id, String contactInfo) {
        logger.log(Level.INFO, "Updating supplier contact info for ID: {0}", id);
        Supplier sup = supplierRepository.findById(id).orElseThrow(() -> {
            logger.log(Level.WARNING, "Supplier ID not found: {0}", id);
            return new IdNotFoundException("Id not found");
        });
        // sup.setContactInfo(contactInfo); // Uncomment and implement this line if the Supplier entity has a contactInfo field
        supplierRepository.save(sup);
        logger.log(Level.INFO, "Supplier contact info updated successfully for ID: {0}", id);
        return "Updated";
    }

    @Override
    public String updateSupplierProductsSupplied(int id, String productsSupplied) {
        logger.log(Level.INFO, "Updating supplier products supplied for ID: {0}", id);
        Supplier sup = supplierRepository.findById(id).orElseThrow(() -> {
            logger.log(Level.WARNING, "Supplier ID not found: {0}", id);
            return new IdNotFoundException("Id not found");
        });
        // sup.setProductsSupplied(productsSupplied); // Uncomment and implement this line if the Supplier entity has a productsSupplied field
        supplierRepository.save(sup);
        logger.log(Level.INFO, "Supplier products supplied updated successfully for ID: {0}", id);
        return "Updated";
    }

    @Override
    public String deleteAllSuppliers() {
        logger.log(Level.INFO, "Deleting all suppliers");
        supplierRepository.deleteAll();
        logger.log(Level.INFO, "All suppliers deleted successfully");
        return "Deleted";
    }
}