package com.project.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Report;

import com.project.exception.IdNotFoundException;
import com.project.exception.InappropriateDateException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.ReportRepository;
import com.project.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	private static final Logger logger = Logger.getLogger(ReportServiceImpl.class.getName());

	@Autowired
	private ReportRepository reportRepo;

	@Override
	public Report create(Report report) throws Exception{
       
		logger.log(Level.INFO, "Creating report: {0}", report);
		
		Report r = reportRepo.save(report);
		if(r==null)
		{
			throw new  Exception("couldnt save the element");
		}
		
		logger.log(Level.INFO, "Report created successfully: {0}", report);
		return r;
	}

	@Override
	public Optional<Report> getReportById(long id) {
		logger.log(Level.INFO, "Fetching report by ID: {0}", id);
		Optional<Report> report = reportRepo.findById(id);
		if (report.isEmpty()) {
			logger.log(Level.WARNING, "Report ID not found: {0}", id);
			throw new IdNotFoundException("Id not found");
		}
		return report;
	}

	@Override
	public List<Report> findAllReports() {
		logger.log(Level.INFO, "Fetching all reports");
		return reportRepo.findAll();
	}

	@Override
	public void deleteById(long id) {
		logger.log(Level.INFO, "Deleting report by ID: {0}", id);
		reportRepo.deleteById(id);
		logger.log(Level.INFO, "Report deleted successfully for ID: {0}", id);
	}

	@Override
	public void updateData(long id, Report report) {
		logger.log(Level.INFO, "Updating report data for ID: {0}", id);
		Report existingReport = reportRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));
		existingReport.setReportType(report.getReportType());
		existingReport.setData(report.getData());
		existingReport.setStartDate(report.getStartDate());
		existingReport.setEndDate(report.getEndDate());
		reportRepo.save(existingReport);
		logger.log(Level.INFO, "Report data updated successfully for ID: {0}", id);
	}

	@Override
	public List<Report> getReportByReportType(String reportType) {
		logger.log(Level.INFO, "Fetching reports by report type: {0}", reportType);
		List<Report> reports = reportRepo.findByReportType(reportType);
		if (reports.isEmpty()) {
			logger.log(Level.WARNING, "Reports not found for report type: {0}", reportType);
			throw new ResourceNotFoundException("report not found");
		}
		return reports;
	}

	@Override
	public List<Report> getReportByDate(LocalDate startDate, LocalDate endDate) {
		logger.log(Level.INFO, "Fetching reports by date range: {0} to {1}", new Object[] { startDate, endDate });
		List<Report> reports = reportRepo.findByStartDateBetween(startDate, endDate);
		if (reports.isEmpty()) {
			logger.log(Level.WARNING, "No reports found for date range: {0} to {1}",
					new Object[] { startDate, endDate });
			throw new InappropriateDateException("Enter correct date {YYYY-MM-DD}");
		}
		return reports;
	}

}