package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierID;
	@NotBlank(message="Name is mandatory")
	
	private String name;
	
	@NotBlank(message = "Contact information is mandatory")
    @Size(max = 12, message = "Contact information should not exceed 12 characters")
	private String contactInfo;
	
	@NotBlank(message = "Products supplied is mandatory")
	@Size(max = 100, message = "Products supplied should not exceed 100 characters")
	private String productsSupplied;

}
