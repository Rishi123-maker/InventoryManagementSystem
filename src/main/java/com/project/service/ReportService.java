package com.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.entity.Report;

public interface ReportService {
    void create(Report report);

    Optional<Report> getReportById(long id);

    List<Report> findAllReports();

    void deleteById(long id);

    void updateData(long id, Report report);

    List<Report> getReportByReportType(String reportType);

    List<Report> getReportByDate(LocalDate startDate, LocalDate endDate);
}