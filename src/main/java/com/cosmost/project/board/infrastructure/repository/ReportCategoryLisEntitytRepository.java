package com.cosmost.project.board.infrastructure.repository;

import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.infrastructure.entity.ReportCategoryListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportCategoryLisEntitytRepository extends JpaRepository<ReportCategoryListEntity, Long> {
    List<ReportCategoryEntity> findByReport_Id(Long id);
}
