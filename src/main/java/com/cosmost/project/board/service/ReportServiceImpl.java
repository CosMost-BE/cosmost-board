package com.cosmost.project.board.service;

import com.cosmost.project.board.exception.CategoryNotFoundException;
import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import com.cosmost.project.board.infrastructure.repository.ReportCategoryEntityRepository;
import com.cosmost.project.board.infrastructure.repository.ReportEntityRepository;
import com.cosmost.project.board.requestbody.CreateReportCategoryRequest;
import com.cosmost.project.board.requestbody.CreateReportRequest;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{

    private final ReportEntityRepository reportEntityRepository;
    private final ReportCategoryEntityRepository reportCategoryEntityRepository;

    public ReportServiceImpl(ReportEntityRepository reportEntityRepository,
                             ReportCategoryEntityRepository reportCategoryEntityRepository) {
        this.reportEntityRepository = reportEntityRepository;
        this.reportCategoryEntityRepository = reportCategoryEntityRepository;
    }

    @Override
    public void createReport(CreateReportRequest createReportRequest) {

        ReportEntity reportEntity = reportDtoToEntity(createReportRequest);
        CreateReportCategoryRequest categoryRequest = new CreateReportCategoryRequest();
        reportEntityRepository.save(reportEntity);

        if(createReportRequest.getReportCategoryName().equals("사용자 신고")  ||
                createReportRequest.getReportCategoryName().equals("리뷰신고") ||
                createReportRequest.getReportCategoryName().equals("사용자 신고")) {

            reportCategoryEntityRepository.save(categoryRequest.reportCategoryDtoToEntity
                    (createReportRequest.getReportCategoryName(), reportEntity));

        } else {
            throw new CategoryNotFoundException();
        }
    }
}
