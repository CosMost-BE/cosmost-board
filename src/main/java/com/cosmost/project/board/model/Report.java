package com.cosmost.project.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Report {

    private Long id;

    private Long reporterId;

    private String reportTitle;

    private String reportContent;

}
