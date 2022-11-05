package com.cosmost.project.board.responsebody;

import com.cosmost.project.board.model.ReportCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadMyCourseReviewsResponse {

    private Long id;
    private Long reporterId;
    private String reportTitle;
    private String reportContent;
    private LocalDate createdAt;
    private List<ReportCategory> reportCategoryList;

    private boolean whetherLastPage;

}
