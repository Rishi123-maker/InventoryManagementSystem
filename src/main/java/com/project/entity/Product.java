package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pid")
	@SequenceGenerator(name = "pid", sequenceName = "pid_seq", initialValue = 1, allocationSize = 1)
	private int productId;
	private String name;
	private String description;
	private double price;
	private String stockLevel;
	

}
