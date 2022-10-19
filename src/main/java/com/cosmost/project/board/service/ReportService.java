package com.cosmost.project.board.service;

import com.cosmost.project.board.model.Report;
import com.cosmost.project.board.requestbody.UpdateReportRequest;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import com.cosmost.project.board.requestbody.CreateReportRequest;

import java.util.List;

public interface ReportService {

    void createReport(CreateReportRequest createReportRequest);
    List<Report> readMyReport();
    void updateReport(Long id, UpdateReportRequest updateReportRequest);
    void deleteReport(Long id);

    List<Report> readAll();

    default ReportEntity createDtoToEntity(CreateReportRequest createReportRequest){

        ReportEntity reportEntity = ReportEntity.builder()
                .reporterId(createReportRequest.getReporterId())
                .reportTitle(createReportRequest.getReportTitle())
                .reportContent(createReportRequest.getReportContent())
                .build();

        return reportEntity;
    }






}
