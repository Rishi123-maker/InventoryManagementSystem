package com.project.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="Products")
public class Product {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;
private String name;
@Column(name="description")
private String desc;
private int price;
private int stockLevel;
@OneToMany(mappedBy="product",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
private List<Order> orders;
public List<Order> getOrders() {
	return orders;
}
public void setOrders(List<Order> orders) {
	this.orders = orders;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getStockLevel() {
	return stockLevel;
}
public void setStockLevel(int stockLevel) {

	this.stockLevel = stockLevel;
}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}


