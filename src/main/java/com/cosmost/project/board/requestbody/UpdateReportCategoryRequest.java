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
public class UpdateReportCategoryRequest {

    private String reportCategoryName;

    public ReportCategoryEntity updateCategoryDtoToEntity(String reportCategoryName, ReportEntity reportEntity){
        return ReportCategoryEntity.builder()
                .id(reportEntity.getId())
                .report(reportEntity)
                .reportCategoryName(reportCategoryName)
                .build();

    }
}
