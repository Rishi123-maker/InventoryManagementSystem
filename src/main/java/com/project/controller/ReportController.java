package com.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.entity.Report;
import com.project.service.ReportService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	
    
	@GetMapping("/admin/getReportById/{id}")
	public ResponseEntity<Optional<Report>> getReportById(@PathVariable long id)

	{

		Optional<Report> report = reportService.getReportById(id);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
 
	@CrossOrigin(origins="*")
	@PostMapping("/admin/create")
	public ResponseEntity<Report> create(@RequestBody Report report) throws Exception {
		
		return new ResponseEntity<>(reportService.create(report), HttpStatus.OK);
	}

	@GetMapping("/admin/findAllReports")
	public ResponseEntity<List<Report>> findAllReports() {
		return new ResponseEntity<List<Report>>(reportService.findAllReports(), HttpStatus.OK);
	}

	@DeleteMapping("/admin/deleteById/{id}")
	public ResponseEntity<String> deleteById(@Valid @PathVariable long id) {
			reportService.deleteById(id);
			return new ResponseEntity<String>(id + " Deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/admin/updateData/{id}")
	public ResponseEntity<String> updateData(@PathVariable long id, @RequestBody Report report) {
		reportService.updateData(id, report);
			return new ResponseEntity<String>(id + " updated successfully", HttpStatus.OK);
	}
    @CrossOrigin(origins="*")
	@GetMapping("/admin/getReportByReportType/{reportType}")
	public ResponseEntity<List<Report>> getReportByReportType(@Valid @PathVariable String reportType) {
    	
		List<Report> reports = reportService.getReportByReportType(reportType);
		return new ResponseEntity<>(reports, HttpStatus.OK);
		
	}

	@GetMapping("/admin/getReportByDate")
	public ResponseEntity<List<Report>> getReportByDate(@Valid @RequestParam LocalDate startDate,
			@Valid @RequestParam LocalDate endDate) {
		List<Report> reports = reportService.getReportByDate(startDate, endDate);
		return new ResponseEntity<>(reports, HttpStatus.OK);
		
	}

}
