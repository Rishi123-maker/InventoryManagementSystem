package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
	@Id
	private int productId;
	@NotNull(message ="this is not null")
	private int quantity;
	
	private String reorderLevel;
	
	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "productId")
	@JsonBackReference
	private Product product;

	
}