package com.cosmost.project.board.service;

import com.cosmost.project.board.exception.CategoryIdNotFoundException;
import com.cosmost.project.board.exception.ReportIdNotFoundException;
import com.cosmost.project.board.infrastructure.entity.ReportCategoryEntity;
import com.cosmost.project.board.infrastructure.entity.ReportCategoryListEntity;
import com.cosmost.project.board.infrastructure.entity.ReportEntity;
import com.cosmost.project.board.infrastructure.repository.ReportCategoryEntityRepository;
import com.cosmost.project.board.infrastructure.repository.ReportCategoryListEntitytRepository;
import com.cosmost.project.board.infrastructure.repository.ReportEntityRepository;
import com.cosmost.project.board.model.Report;
import com.cosmost.project.board.model.ReportCategory;
import com.cosmost.project.board.requestbody.CreateReportCategoryListRequest;
import com.cosmost.project.board.requestbody.CreateReportRequest;
import com.cosmost.project.board.requestbody.UpdateReportCategoryListRequest;
import com.cosmost.project.board.requestbody.UpdateReportRequest;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final ReportEntityRepository reportEntityRepository;
    private final ReportCategoryEntityRepository reportCategoryEntityRepository;
    private final ReportCategoryListEntitytRepository reportCategoryListEntitytRepository;

    @Autowired
    public ReportServiceImpl(ReportEntityRepository reportEntityRepository,
                             ReportCategoryEntityRepository reportCategoryEntityRepository,
                             ReportCategoryListEntitytRepository reportCategoryListEntitytRepository) {
        this.reportEntityRepository = reportEntityRepository;
        this.reportCategoryEntityRepository = reportCategoryEntityRepository;
        this.reportCategoryListEntitytRepository = reportCategoryListEntitytRepository;
    }

    @Override
    public void createReport(CreateReportRequest createReportRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = request.getHeader("Authorization");
        Long authorId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        ReportEntity reportEntity = ReportEntity.builder()
                .reporterId(authorId)
                .reportTitle(createReportRequest.getReportTitle())
                .reportContent(createReportRequest.getReportContent())
                .createdAt(createReportRequest.getCreatedAt())
                .build();
        reportEntityRepository.save(reportEntity);

        for (CreateReportCategoryListRequest categoryListRequest : createReportRequest.getCreateReportCategoryListRequestList()) {
            Optional<ReportCategoryEntity> reportCategory = Optional.ofNullable(reportCategoryEntityRepository.findById(categoryListRequest.getReportCategory())
                    .orElseThrow(CategoryIdNotFoundException::new));

            reportCategoryListEntitytRepository.save(categoryListRequest.createCategoryDtoToEntity(reportEntity, reportCategory.get()));
        }
    }

    @Override
    public List<Report> readMyReport() {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Long reporterId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());


        List<ReportEntity> reportEntityList = reportEntityRepository.findAllByReporterId(Long.valueOf(reporterId));
        List<Report> reportList = new ArrayList<>();

        reportEntityList.forEach(reportEntity -> {

            List<ReportCategoryListEntity> reportCategoryListEntityList =
                    reportCategoryListEntitytRepository.findByReport_Id(reportEntity.getId());
            List<ReportCategory> reportCategoryList = new ArrayList<>();

            reportCategoryListEntityList.forEach(reportCategoryEntity -> {
                reportCategoryList.add(ReportCategory.builder()
                        .id(reportCategoryEntity.getId())
                        .reportCategoryName(reportCategoryEntity.getReportCategory().getReportCategoryName())
                        .build());
            });

            reportList.add(Report.builder()
                    .id(reportEntity.getId())
                    .createdAt(reportEntity.getCreatedAt())
                    .reporterId(reportEntity.getReporterId())
                    .reportTitle(reportEntity.getReportTitle())
                    .reportContent(reportEntity.getReportContent())
                    .reportCategoryList(reportCategoryList)
                    .build());

        });
        return reportList;
    }

    @Override
    public void updateReport(Long id, UpdateReportRequest updateReportRequest) {
        doUpdateReport(id, updateReportRequest);
    }

    @Override
    public List<ReportCategoryEntity> readAll() {
        return reportCategoryEntityRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteReport(Long id) {
        Optional<ReportEntity> reportId = Optional.ofNullable(reportEntityRepository
                .findById(id).orElseThrow(ReportIdNotFoundException::new));

        List<ReportCategoryListEntity> reportCategoryListEntity =
                reportCategoryListEntitytRepository.findByReport_Id(reportId.get().getId());

        for (ReportCategoryListEntity temp : reportCategoryListEntity) {
            reportCategoryListEntitytRepository.deleteById(temp.getId());
        }
        reportEntityRepository.deleteById(id);
    }

    private ReportEntity doUpdateReport(Long id, UpdateReportRequest updateReportRequest) {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = request.getHeader("Authorization");
        Long reporterId = Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());

        Optional<ReportEntity> reportEntity = Optional.of(reportEntityRepository.findById(id)
                .orElseThrow(ReportIdNotFoundException::new));

        if (reportEntity.isPresent()) {

            ReportEntity updatedReport = reportEntityRepository.save(ReportEntity.builder()
                    .id(id)
                    .reporterId(Long.valueOf(reporterId))
                    .reportTitle(updateReportRequest.getReportTitle())
                    .reportContent(updateReportRequest.getReportContent())
                    .build());

            for (UpdateReportCategoryListRequest updateReportCategoryListRequest : updateReportRequest.getUpdateReportCategoryListRequestList()) {
                Optional<ReportCategoryEntity> reportCategory =
                        reportCategoryEntityRepository.findById(updateReportCategoryListRequest.getReportCategory());

                reportCategoryListEntitytRepository.save(ReportCategoryListEntity.builder()
                        .id(id)
                        .report(updatedReport)
                        .reportCategory(reportCategory.get())
                        .build());
            }

        }
        return null;
    }
}
