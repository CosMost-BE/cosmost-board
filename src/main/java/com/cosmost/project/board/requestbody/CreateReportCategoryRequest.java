package com.cosmost.project.board.requestbody;

import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReportCategoryRequest {

    private String reportCategoryName;

    public ReportCategoryEntity reportCategoryDtoToEntity(String reportCategoryName,
                                                          ReportEntity reportEntity){

        return ReportCategoryEntity.builder()
                .report(reportEntity)
                .reportCategoryName(reportCategoryName)
                .build();

    }
}
