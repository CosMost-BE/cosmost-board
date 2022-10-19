package com.cosmost.project.board.requestbody;

import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReportRequest {

    private Long reporterId;
    private String reportTitle;
    private String reportContent;
    private String reportCategoryName;

    private List<CreateReportCategoryListRequest> createReportCategoryListRequestList;

    public ReportEntity createDtoToEntity(CreateReportRequest createReportRequest) {

        return ReportEntity.builder()
                .reporterId(createReportRequest.getReporterId())
                .reportTitle(createReportRequest.getReportTitle())
                .reportContent(createReportRequest.getReportContent())
                .build();
    }

}
