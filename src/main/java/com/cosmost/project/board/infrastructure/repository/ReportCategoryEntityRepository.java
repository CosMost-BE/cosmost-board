package com.cosmost.project.board.infrastructure.repository;

import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCategoryEntityRepository extends JpaRepository<ReportCategoryEntity, Long> {
}
