package com.cosmost.project.board.requestbody;

import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.infrastructure.entity.ReportCategoryListEntity;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateReportCategoryListRequest {

    private Long report;
    private Long reportCategory;

    public ReportCategoryListEntity createCategoryDtoToEntity(ReportEntity reportEntity,
                                                              ReportCategoryEntity reportCategoryEntity) {

        return ReportCategoryListEntity.builder()
                .report(reportEntity)
                .reportCategory(reportCategoryEntity)
                .build();
    }


}
