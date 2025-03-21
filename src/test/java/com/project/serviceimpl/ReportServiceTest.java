package com.project.serviceimpl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.project.entity.Report;
import com.project.exception.IdNotFoundException;
import com.project.exception.InappropriateDateException;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.ReportRepository;
import com.project.service.ReportService;
import com.project.serviceimpl.ReportServiceImpl;
 
@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
 
    @Mock
    private ReportRepository reportRepo;
 
    @InjectMocks
    private ReportServiceImpl reportService;
 
    private Report report;
 
    @BeforeEach
    void setUp() {
        report = new Report();
        //report.setId(1L);
        long id = 1l;
        report.setReportType("Type1");
        report.setData("Sample Data");
        report.setStartDate(LocalDate.of(2023, 1, 1));
        report.setEndDate(LocalDate.of(2023, 12, 31));
    }
 
    @Test
    void testCreate() {
        reportService.create(report);
        verify(reportRepo, times(1)).save(report);
    }
 
    @Test
    void testGetReportById() {
        when(reportRepo.findById(1L)).thenReturn(Optional.of(report));
        Optional<Report> foundReport = reportService.getReportById(1L);
        assertTrue(foundReport.isPresent());
        assertEquals(report, foundReport.get());
    }
 
    @Test
    void testGetReportById_NotFound() {
        when(reportRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> reportService.getReportById(1L));
    }
 
    @Test
    void testFindAllReports() {
        List<Report> reports = Arrays.asList(report);
        when(reportRepo.findAll()).thenReturn(reports);
        List<Report> foundReports = reportService.findAllReports();
        assertEquals(1, foundReports.size());
        assertEquals(report, foundReports.get(0));
    }
 
    @Test
    void testDeleteById() {
        reportService.deleteById(1L);
        verify(reportRepo, times(1)).deleteById(1L);
    }
 
    @Test
    void testUpdateData() {
        when(reportRepo.findById(1L)).thenReturn(Optional.of(report));
        report.setReportType("UpdatedType");
        reportService.updateData(1L, report);
        verify(reportRepo, times(1)).save(report);
    }
 
    @Test
    void testGetReportByReportType() {
        List<Report> reports = Arrays.asList(report);
        when(reportRepo.findByReportType("Type1")).thenReturn(reports);
        List<Report> foundReports = reportService.getReportByReportType("Type1");
        assertEquals(1, foundReports.size());
        assertEquals(report, foundReports.get(0));
    }
 
    @Test
    void testGetReportByReportType_NotFound() {
        when(reportRepo.findByReportType("Type1")).thenReturn(Arrays.asList());
        assertThrows(ResourceNotFoundException.class, () -> reportService.getReportByReportType("Type1"));
    }
 
    @Test
    void testGetReportByDate() {
        List<Report> reports = Arrays.asList(report);
        when(reportRepo.findByStartDateBetween(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31))).thenReturn(reports);
        List<Report> foundReports = reportService.getReportByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
        assertEquals(1, foundReports.size());
        assertEquals(report, foundReports.get(0));
    }
 
    @Test
    void testGetReportByDate_NotFound() {
        when(reportRepo.findByStartDateBetween(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31))).thenReturn(Arrays.asList());
        assertThrows(InappropriateDateException.class, () -> reportService.getReportByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)));
    }
}
