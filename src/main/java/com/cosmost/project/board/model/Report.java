package com.cosmost.project.board.model;

import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class Report {

    private Long id;
    private Long reporterId;

    private String reportTitle;

    private String reportContent;
    private LocalDate createdAt;
    private List<ReportCategory> reportCategoryList;

    public Report(ReportEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.reporterId = entity.getReporterId();
        this.reportTitle = entity.getReportTitle();
        this.reportContent = entity.getReportContent();
        this.createdAt = entity.getCreatedAt();
        this.reportCategoryList = getReportCategoryList();
    }

}
