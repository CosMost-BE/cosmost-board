package com.cosmost.project.board.service;

import com.cosmost.project.board.requestbody.UpdateReportRequest;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import com.cosmost.project.board.requestbody.CreateReportRequest;

public interface ReportService {

    void createReport(CreateReportRequest createReportRequest);

    void updateReport(Long id, UpdateReportRequest updateReportRequest);

    default ReportEntity createDtoToEntity(CreateReportRequest createReportRequest){

        ReportEntity reportEntity = ReportEntity.builder()
                .reporterId(createReportRequest.getReporterId())
                .reportTitle(createReportRequest.getReportTitle())
                .reportContent(createReportRequest.getReportContent())
                .build();

        return reportEntity;
    }





}
