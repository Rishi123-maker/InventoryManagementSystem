package com.project.entity;

//import java.sql.Date;
import java.time.LocalDate;


import com.project.validation.ValidName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reportId;
	@ValidName
	private String reportType;
	@NotNull(message="Please enter valid date")
	private LocalDate startDate;
	@NotNull(message="Please enter valid date")
	private LocalDate endDate;
	@NotBlank(message="Please enter data")
	private String data;
	

	

}
