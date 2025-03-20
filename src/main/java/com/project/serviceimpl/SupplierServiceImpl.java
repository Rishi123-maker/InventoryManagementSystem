//package com.project.serviceimpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.entity.Supplier;
//import com.project.exception.IdNotFoundException;
//import com.project.repository.SupplierRepository;
//
//
//
//@Service
//public class SupplierServiceImpl {
//	
//	@Autowired
//	private SupplierRepository supplierRepository;
//
//	public List<Supplier> getAllSuppliers() {
//		// TODO Auto-generated method stub
//		return supplierRepository.findAll();
//	}
//
//	public String createSupplierDetails(Supplier s) {
//		// TODO Auto-generated method stub
//		supplierRepository.save(s);
//		return "Inserted";
//	}
//
//	public Supplier getSupplierById(int id) {
//		// TODO Auto-generated method stub
//		return supplierRepository.findById(id).orElseThrow(()->new IdNotFoundException("Id has not been found"));
//	}
//
//	public String deleteSupplierById(int id) {
//		// TODO Auto-generated method stub
//		supplierRepository.deleteById(id);
//		return "deleted";
//	}
//
//	public String updateSupplier(int id, String name) {
//		// TODO Auto-generated method stub
//		Supplier sup = supplierRepository.findById(id).get();
////		sup.setName(name);
//		if(sup==null)
//		{
//			throw new IdNotFoundException("Id not found");
//		}
//		supplierRepository.save(sup);
//		return "Updated";
//		
//	}
//
//	public Supplier getSupplierByName(String name) {
//		// TODO Auto-generated method stub
//		return supplierRepository.findByName(name).orElse(null);
//	}
//
//	public String updateSupplierContactInfo(int id, String contactInfo) {
//		// TODO Auto-generated method stub
//		Supplier sup = supplierRepository.findById(id).get();
//		if(sup==null)
//		{
//			throw new IdNotFoundException("Id not found");
//		}
////		sup.setContactInfo(contactInfo);
//		supplierRepository.save(sup);
//		return "Updated";
//	}
//
//	public String updateSupplierProductsSupplied(int id, String productsSupplied) {
//		// TODO Auto-generated method stub
//		Supplier sup = supplierRepository.findById(id).get();
//		if(sup==null)
//		{
//			throw new IdNotFoundException("Id not found");
//		}
////		sup.setProductsSupplied(productsSupplied);
//		supplierRepository.save(sup);
//		return "Updated";
//	}
//
//	public String deleteAllSuppliers() {
//	    supplierRepository.deleteAll();
//	    return "Deleted";
//	}
//}



package com.project.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Supplier;
import com.project.exception.IdNotFoundException;
import com.project.repository.SupplierRepository;
import com.project.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public String createSupplierDetails(Supplier s) {
        supplierRepository.save(s);
        return "Inserted";
    }

    @Override
    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id has not been found"));
    }

    @Override
    public String deleteSupplierById(int id) {
        supplierRepository.deleteById(id);
        return "deleted";
    }

    @Override
    public String updateSupplier(int id, String name) {
        Supplier sup = supplierRepository.findById(id).get();
        if (sup == null) {
            throw new IdNotFoundException("Id not found");
        }
        // sup.setName(name); // Uncomment and implement this line if the Supplier entity has a name field
        supplierRepository.save(sup);
        return "Updated";
    }

    @Override
    public Supplier getSupplierByName(String name) {
        return supplierRepository.findByName(name).orElse(null);
    }

    @Override
    public String updateSupplierContactInfo(int id, String contactInfo) {
        Supplier sup = supplierRepository.findById(id).get();
        if (sup == null) {
            throw new IdNotFoundException("Id not found");
        }
        // sup.setContactInfo(contactInfo); // Uncomment and implement this line if the Supplier entity has a contactInfo field
        supplierRepository.save(sup);
        return "Updated";
    }

    @Override
    public String updateSupplierProductsSupplied(int id, String productsSupplied) {
        Supplier sup = supplierRepository.findById(id).get();
        if (sup == null) {
            throw new IdNotFoundException("Id not found");
        }
        // sup.setProductsSupplied(productsSupplied); // Uncomment and implement this line if the Supplier entity has a productsSupplied field
        supplierRepository.save(sup);
        return "Updated";
    }

    @Override
    public String deleteAllSuppliers() {
        supplierRepository.deleteAll();
        return "Deleted";
    }
}
