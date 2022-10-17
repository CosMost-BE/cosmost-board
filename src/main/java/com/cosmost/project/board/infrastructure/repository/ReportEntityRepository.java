package com.cosmost.project.board.infrastructure.repository;

import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportEntityRepository extends JpaRepository<ReportEntity, Long> {
}
