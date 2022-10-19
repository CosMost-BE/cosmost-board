package com.cosmost.project.board.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReportRequest {


    private Long id;
    private String reportTitle;
    private String reportContent;

    private List<UpdateReportCategoryListRequest> updateReportCategoryListRequestList;

}
