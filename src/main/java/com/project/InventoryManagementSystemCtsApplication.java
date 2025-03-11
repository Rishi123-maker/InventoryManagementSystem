package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ComponentScan(basePackages= {"com.project", "com.project.repository"})
public class InventoryManagementSystemCtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemCtsApplication.class, args);

	}

}