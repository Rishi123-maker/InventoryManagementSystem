package com.project.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int orderId;

@ManyToOne
@JoinColumn(name="customerId", referencedColumnName="id")
@JsonIgnoreProperties({"username","password","role"})
private User customer;

@ManyToOne
@JoinColumn(name="productId")
@JsonIgnoreProperties({"stockLevel","stock"})
@JsonBackReference
private Product product;

public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
@NotNull(message="This is not null")
private int quantity;
@NotNull
private LocalDate orderDate;
@NotNull
private String status;
public Order() {
	super();
}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}


public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public LocalDate getOrderDate() {
	return orderDate;
}
public void setOrderDate(LocalDate orderDate) {
	this.orderDate = orderDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

public User getCustomer() {
	return customer;
}
public void setCustomer(User customer) {
	this.customer = customer;
}

}
