package com.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entity.Report;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{

	
	public Optional<Report> findById(long id);
	public List<Report> findByReportType(String reportType);

	//public Report findReportByDate(LocalDate date);
	
	public List<Report> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
	
}
