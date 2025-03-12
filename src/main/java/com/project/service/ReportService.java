package com.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.project.entity.Report;
import com.project.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepo;

	public void create(Report report) {
		reportRepo.save(report);
	}

	public Optional<Report> getReportById(long id) {
		return reportRepo.findById(id);
	}

	public List<Report> findAllReports() {

		return reportRepo.findAll();
	}
	
	public void deleteById(long id) {
		reportRepo.deleteById(id);
	}
	
	public void updateData(Report report) {
		reportRepo.save(report);
	}
	
	public List<Report> getReportByReportType(String reportType) {
		return reportRepo.findByReportType(reportType);
	}
	
	public List<Report> getReportByDate(LocalDate startDate, LocalDate endDate){
		return reportRepo.findByStartDateBetween(startDate, endDate);
	}
	
}
