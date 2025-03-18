package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    private int productId;

    private int quantity;
    private String reorderLevel;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "productId")
    @JsonBackReference // Helps prevent infinite recursion
    private Product product;
    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(String reorderLevel) { this.reorderLevel = reorderLevel; }
}