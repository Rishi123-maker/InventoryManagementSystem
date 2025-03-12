package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	Optional<Supplier> findByName(String name);

}