package com.project.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "customerId", referencedColumnName = "id")
	@JsonIgnoreProperties({ "username", "password", "role","orders"})
	private User customer;
   
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "productId")
	@JsonBackReference
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@NotNull(message = "This is not null")
	private int quantity;
	@NotNull(message="This is not null")
	private LocalDate orderDate;
	@NotBlank(message="This is not Blank")
	private String status;

	

}
