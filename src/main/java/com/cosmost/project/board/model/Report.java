package com.cosmost.project.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
    private List<ReportCategory> reportCategoryList;

}
