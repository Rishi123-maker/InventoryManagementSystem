package com.project;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.entity.Report;
import com.project.repository.ReportRepository;

@SpringBootTest
class InventoryManagementSystemCtsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	ReportRepository repo;
	
//	@Test
//	public void testGetReportById() {
//		long id = 1l;
//		//Report report = new Report();
//		Report report = repo.getById(id);
//		assertTrue(report.isPresent());
//	    assertEquals(1L, report.get().getId());
//		System.out.println(report);
//	}

}
