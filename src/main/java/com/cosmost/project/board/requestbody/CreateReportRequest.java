package com.cosmost.project.board.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate createdAt;

    private List<CreateReportCategoryListRequest> createReportCategoryListRequestList;
}
