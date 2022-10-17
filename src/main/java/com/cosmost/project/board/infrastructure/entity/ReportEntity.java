package com.cosmost.project.board.infrastructure.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "report")
public class ReportEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long reporterId;

    @NotNull
    private String reportTitle;

    @NotNull
    private String reportContent;


    @Builder
    public ReportEntity(Long id, Long reporterId, String reportTitle, String reportContent) {
        this.id = id;
        this.reporterId = reporterId;
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
    }
}
