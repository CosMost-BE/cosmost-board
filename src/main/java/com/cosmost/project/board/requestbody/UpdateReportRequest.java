package com.cosmost.project.board.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReportRequest {


    private Long id;
    private String reportTitle;
    private String reportContent;
    private String reportCategoryName;



}
