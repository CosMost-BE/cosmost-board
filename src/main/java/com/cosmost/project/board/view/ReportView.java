package com.cosmost.project.board.view;

import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.model.ReportCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportView {

    private Long id;
    private Long reporterId;
    private String reportTitle;
    private String reportContent;

    private List<ReportCategory> categoryEntityList;
}
