package com.cosmost.project.board.service;

import com.cosmost.project.board.exception.CategoryNotFoundException;
import com.cosmost.project.board.exception.ReportIdNotFoundException;
import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.model.Report;
import com.cosmost.project.board.requestbody.UpdateReportCategoryRequest;
import com.cosmost.project.board.requestbody.UpdateReportRequest;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import com.cosmost.project.board.infrastructure.repository.ReportCategoryEntityRepository;
import com.cosmost.project.board.infrastructure.repository.ReportEntityRepository;
import com.cosmost.project.board.requestbody.CreateReportCategoryRequest;
import com.cosmost.project.board.requestbody.CreateReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportEntityRepository reportEntityRepository;
    private final ReportCategoryEntityRepository reportCategoryEntityRepository;

    @Autowired
    public ReportServiceImpl(ReportEntityRepository reportEntityRepository,
                             ReportCategoryEntityRepository reportCategoryEntityRepository) {
        this.reportEntityRepository = reportEntityRepository;
        this.reportCategoryEntityRepository = reportCategoryEntityRepository;
    }

    @Override
    public void createReport(CreateReportRequest createReportRequest) {

        ReportEntity reportEntity = createDtoToEntity(createReportRequest);
        CreateReportCategoryRequest categoryRequest = new CreateReportCategoryRequest();
        reportEntityRepository.save(reportEntity);

        if (createReportRequest.getReportCategoryName().equals("사용자 신고") ||
                createReportRequest.getReportCategoryName().equals("리뷰신고") ||
                createReportRequest.getReportCategoryName().equals("코스 신고")) {

            reportCategoryEntityRepository.save(categoryRequest.createCategoryDtoToEntity
                    (createReportRequest.getReportCategoryName(), reportEntity));

        } else {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public void updateReport(Long id, UpdateReportRequest updateReportRequest) {
        doUpdateReport(id, updateReportRequest);

    }

    private ReportEntity doUpdateReport(Long id, UpdateReportRequest updateReportRequest) {

        Optional<ReportEntity> reportEntity = Optional.of(reportEntityRepository.findById(id)
                .orElseThrow(ReportIdNotFoundException::new));
        UpdateReportCategoryRequest updateReportCategoryRequest = new UpdateReportCategoryRequest();

        if (reportEntity.isPresent()) {
            ReportEntity updatedReport = reportEntityRepository.save(ReportEntity.builder()
                    .id(id)
                    .reporterId(reportEntity.get().getReporterId())
                    .reportTitle(updateReportRequest.getReportTitle())
                    .reportContent(updateReportRequest.getReportContent())
                    .build());

            ReportCategoryEntity updatedCategory = updateReportCategoryRequest.updateCategoryDtoToEntity
                    (updateReportRequest.getReportCategoryName(), updatedReport);
            reportCategoryEntityRepository.save(updatedCategory);

        }
        return null;
    }
}
