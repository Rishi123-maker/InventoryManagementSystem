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
import com.project.exception.IdNotFoundException;
import com.project.exception.ResourceNotFoundException;
import com.project.service.ReportService;
import com.project.serviceimpl.ReportServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/")
	public ResponseEntity<String> Testing()
	{
		return new ResponseEntity<String>("Hi this is Reports",HttpStatus.OK);
	}
	@GetMapping("/admin/getReportById/{id}")
	public ResponseEntity<Optional<Report>> getReportById(@PathVariable long id)

	{
		
			Optional<Report> report = reportService.getReportById(id);
			if (report == null) {
				throw new IdNotFoundException("god bless you harsha");
			}
			return new ResponseEntity<>(report,HttpStatus.OK);
	}
	
	@PostMapping("/admin/create")
	public ResponseEntity<String> create(@RequestBody Report report) {
		reportService.create(report);
		return new ResponseEntity<>("Successfully created an entry",HttpStatus.OK);
	}
	

//	@GetMapping("/findAllReports")
//	public ResponseEntity<List<Report>> findAllReports(){
//		List<Report> reports = reportService.findAllReports();
//        if (reports.isEmpty()) {
//            throw new ResourceNotFoundException("No reports found");
//        }
//        return new ResponseEntity<>(reports, HttpStatus.OK);
//		//return new ResponseEntity<List<Report>>(reportService.findAllReports(),HttpStatus.OK);
//	}
	
	
	
//	@DeleteMapping("/deleteById/{id}")
//	public ResponseEntity<String> deleteById(@PathVariable long id){
//		Report report = reportService.getReportById(id).orElse(null);
//		if(report!=null) {
//			reportService.deleteById(id);
//			return new ResponseEntity<String>(id+" Deleted successfully",HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<String>(id+" doesnot exist",HttpStatus.BAD_REQUEST);
//		}
//		
//	}
	
	

	@GetMapping("/admin/findAllReports")
	public ResponseEntity<List<Report>> findAllReports(){
		return new ResponseEntity<List<Report>>(reportService.findAllReports(),HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/deleteById/{id}")
	public ResponseEntity<String> deleteById(@Valid @PathVariable long id){
		Report report = reportService.getReportById(id).orElse(null);
		if(report!=null) {
			reportService.deleteById(id);
			return new ResponseEntity<String>(id+" Deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(id+" doesnot exist",HttpStatus.BAD_REQUEST);
		}

	}

	

	
	

	@PutMapping("/admin/updateData/{id}")
	public ResponseEntity<String> updateData(@PathVariable long id, @RequestBody Report report){
		Report rep = reportService.getReportById(id).orElse(null);
		if(rep!=null) {
			reportService.updateData(id, report);
			return new ResponseEntity<String>(id+" updated successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(id+" doesnot exist",HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/admin/getReportByReportType/{reportType}")
	public ResponseEntity<List<Report>> getReportByReportType(@Valid @PathVariable String reportType){
		List<Report> reports = reportService.getReportByReportType(reportType);
        if (reports.isEmpty()) {
            throw new ResourceNotFoundException("No reports found");
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
		//return new ResponseEntity<List<Report>>(reportService.getReportByReportType(reportType),HttpStatus.OK);		one line return statement
	}
	
	@GetMapping("/admin/getReportByDate")
	public ResponseEntity<List<Report>> getReportByDate(@Valid @RequestParam LocalDate startDate,@Valid @RequestParam LocalDate endDate){
		List<Report> reports = reportService.getReportByDate(startDate, endDate);
        if (reports.isEmpty()) {
            throw new ResourceNotFoundException("No reports found");
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
		//return new ResponseEntity<List<Report>>(reportService.getReportByDate(startDate, endDate),HttpStatus.OK);
	}
	
	
}
