package com.cosmost.project.board.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportCategoryView {

    private Long id;
    private Long reporterId;
    private String reportTitle;
    private String reportContent;
    private String reportCategoryName;

}
