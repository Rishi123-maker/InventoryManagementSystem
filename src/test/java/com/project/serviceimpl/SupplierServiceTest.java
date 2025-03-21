package com.project.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.entity.Supplier;
import com.project.exception.IdNotFoundException;
import com.project.repository.SupplierRepository;

public class SupplierServiceTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSuppliers() {
        Supplier supplier1 = new Supplier();
        Supplier supplier2 = new Supplier();
        List<Supplier> suppliers = Arrays.asList(supplier1, supplier2);

        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.getAllSuppliers();
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateSupplierDetails() {
        Supplier supplier = new Supplier();
        supplierService.createSupplierDetails(supplier);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void testGetSupplierById() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(1)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.getSupplierById(1);
        assertNotNull(result);
    }

    @Test
    public void testGetSupplierById_NotFound() {
        when(supplierRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            supplierService.getSupplierById(1);
        });
    }

    @Test
    public void testDeleteSupplierById() {
        supplierService.deleteSupplierById(1);
        verify(supplierRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdateSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(1)).thenReturn(Optional.of(supplier));

        String result = supplierService.updateSupplier(1, "New Name");
        assertEquals("Updated", result);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void testUpdateSupplier_NotFound() {
        when(supplierRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {
            supplierService.updateSupplier(1, "New Name");
        });
    }

    @Test
    public void testGetSupplierByName() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findByName("SupplierName")).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.getSupplierByName("SupplierName");
        assertNotNull(result);
    }

    @Test
    public void testUpdateSupplierContactInfo() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(1)).thenReturn(Optional.of(supplier));

        String result = supplierService.updateSupplierContactInfo(1, "New Contact Info");
        assertEquals("Updated", result);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void testUpdateSupplierProductsSupplied() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(1)).thenReturn(Optional.of(supplier));

        String result = supplierService.updateSupplierProductsSupplied(1, "New Products Supplied");
        assertEquals("Updated", result);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    public void testDeleteAllSuppliers() {
        supplierService.deleteAllSuppliers();
        verify(supplierRepository, times(1)).deleteAll();
    }
}