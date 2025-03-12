package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sid_seq")
	@SequenceGenerator(name = "sid_seq", sequenceName = "sid_sequence", initialValue = 1, allocationSize = 1)
	private int supplierID;
	private String name;
	private String contactInfo;
	private String productsSupplied;
	

}
