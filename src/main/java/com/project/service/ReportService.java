package com.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.project.entity.Report;
import com.project.exception.IdNotFoundException;
import com.project.exception.InappropriateDateException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepo;

	public void create(Report report)  {
		
		reportRepo.save(report);
	}

	public Optional<Report> getReportById(long id) {
		Optional <Report> report =reportRepo.findById(id);
		
		if(report.isEmpty())
		{
			throw new IdNotFoundException("Id not found");
		}
		return report;
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
		List<Report> reports =reportRepo.findByReportType(reportType);
		if(reports.size()==0)
		{
			throw new ResourceNotFoundException("report not found");
		}
		return  reports;
	}
	
	public List<Report> getReportByDate(LocalDate startDate, LocalDate endDate){
		List<Report> reports =reportRepo.findByStartDateBetween(startDate, endDate);
		if(reports.size()==0)
		{
			throw new InappropriateDateException("Enter correct date");
		}
		return reports;
	}
	
}
