package com.cosmost.project.board.infrastructure.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "report_category_list")
public class ReportCategoryListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    ReportEntity report;

    @ManyToOne(fetch = FetchType.LAZY)
    ReportCategoryEntity reportCategory;

    @Builder
    public ReportCategoryListEntity(Long id, ReportEntity report, ReportCategoryEntity reportCategory) {
        this.id = id;
        this.report = report;
        this.reportCategory = reportCategory;
    }

}
