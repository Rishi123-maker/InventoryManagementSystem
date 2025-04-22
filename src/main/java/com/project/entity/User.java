package com.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@Column(unique = true)
	private String username;
	private String password;
	private String role;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private List<Order> orders;

	public User(@Email String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.orders = null;
	}

	public User() {
		super();
	}

}