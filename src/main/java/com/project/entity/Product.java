package com.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.entity.enums.StockLevel;
import com.project.validation.ValidName;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@ValidName
	@Column(unique=true)
	private String name;
	@Column(name = "description")
	private String desc;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	@JsonManagedReference
	private List<Order> orders;
	
	@NotNull(message="Please enter price of the product")
	private Double price;
	
	@Enumerated(EnumType.STRING)
	private StockLevel stockLevel;
	
	@ToString.Exclude
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL) 
	@JsonManagedReference // Helps prevent infinite recursion
	@JsonIgnoreProperties("productId")
	private Stock stock;
}
