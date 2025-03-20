package com.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.validation.ValidName;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private int productId;
    
    
    @ValidName
    private String name;
    
    @NotBlank(message="this is not valid")
    @Column(name = "description")
    private String desc;
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL)
    private List<Order> orders;
    
   @NotNull
    private double price;
   @NotNull
    private int stockLevel;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)// orphanRemoval = true)
  @JsonManagedReference // Helps prevent infinite recursion
    private Stock stock;
//
//    @Version
//    private int version;

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public double getPrice() { return price; }
    public void setPrice(double price2) { this.price = price2; }

    public int getStockLevel() { return stockLevel; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//    public int getVersion() { return version; }
//    public void setVersion(int version) { this.version = version; }
//
    public Stock getStock() { return stock; }
    public void setStock(Stock stock) {
        this.stock = stock;
        if (stock != null) {
            stock.setProduct(this); // Ensuring bidirectional mapping
        }
    }
}
