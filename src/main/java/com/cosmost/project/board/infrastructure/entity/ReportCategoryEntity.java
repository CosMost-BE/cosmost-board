package com.cosmost.project.board.infrastructure.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "report_category")
public class ReportCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportCategoryName;

    @Builder
    public ReportCategoryEntity(Long id, String reportCategoryName) {
        this.id = id;
        this.reportCategoryName = reportCategoryName;
    }

}
