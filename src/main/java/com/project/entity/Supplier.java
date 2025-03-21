package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sid_seq")
	@SequenceGenerator(name = "sid_seq", sequenceName = "sid_sequence", initialValue = 1, allocationSize = 1)

	private int supplierID;
	@NotNull
	private String name;
	@NotNull
	private String contactInfo;
	@NotNull
	private String productsSupplied;

}
