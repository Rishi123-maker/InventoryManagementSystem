package com.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/")
	public ResponseEntity<String> Testing()
	{
		return new ResponseEntity<String>("Hi this is Reports",HttpStatus.OK);
	}
	
	@GetMapping("/getReportById/{id}")
	public ResponseEntity<Optional<Report>> getReportById(@PathVariable long id)
	{
		return new ResponseEntity<>(reportService.getReportById(id),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody Report report) {
		reportService.create(report);
		return new ResponseEntity<>("Successfully created an entry",HttpStatus.OK);
	}
	
	@GetMapping("/findAllReports")
	public ResponseEntity<List<Report>> findAllReports(){
		return new ResponseEntity<List<Report>>(reportService.findAllReports(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id){
		Report report = reportService.getReportById(id).orElse(null);
		if(report!=null) {
			reportService.deleteById(id);
			return new ResponseEntity<String>(id+" Deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(id+" doesnot exist",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/updateData/{id}")
	public ResponseEntity<String> updateData(@PathVariable long id, @RequestBody Report report){
		Report rep = reportService.getReportById(id).orElse(null);
		if(rep!=null) {
			reportService.updateData(report);
			return new ResponseEntity<String>(id+" updated successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(id+" doesnot exist",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getReportByReportType/{reportType}")
	public ResponseEntity<List<Report>> getReportByReportType(@PathVariable String reportType){
		return new ResponseEntity<List<Report>>(reportService.getReportByReportType(reportType),HttpStatus.OK);
	}
	
	@GetMapping("/getReportByDate")
	public ResponseEntity<List<Report>> getReportByDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
		return new ResponseEntity<List<Report>>(reportService.getReportByDate(startDate, endDate),HttpStatus.OK);
	}
	
	
}
