package com.cosmost.project.board.infrastructure.repository;

import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportEntityRepository extends JpaRepository<ReportEntity, Long> {
    Slice<ReportEntity> findAllByReporterId(Long id, Pageable pageable);

    ReportEntity findByReporterIdAndId(Long reporterId, Long id);
}
