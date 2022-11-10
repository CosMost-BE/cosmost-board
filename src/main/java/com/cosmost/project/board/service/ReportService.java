package com.cosmost.project.board.service;

import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.requestbody.CreateReportRequest;
import com.cosmost.project.board.requestbody.UpdateReportRequest;
import com.cosmost.project.board.responsebody.ReadMyCourseReviewsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportService {

    void createReport(CreateReportRequest createReportRequest);
    List<ReadMyCourseReviewsResponse> readMyReport(Pageable pageable);
    void updateReport(Long id, UpdateReportRequest updateReportRequest);
    void deleteReport(Long id);
    List<ReportCategoryEntity> readAll();



}
