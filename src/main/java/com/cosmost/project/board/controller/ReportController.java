package com.cosmost.project.board.controller;

import com.cosmost.project.board.requestbody.CreateReportRequest;
import com.cosmost.project.board.requestbody.UpdateReportRequest;
import com.cosmost.project.board.service.ReportService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/v1/boards")
public class ReportController {

    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @ApiResponses({
            @ApiResponse(code = 201, message = "리뷰 등록완료 !!!!!!"),
            @ApiResponse(code = 401, message = "리뷰가 등록되지 않았습니다, 다시 확인하세요"),
            @ApiResponse(code = 403, message = "권한이 존재하지 않습니다."),
            @ApiResponse(code = 404, message = "리뷰를 찾을 수 없습니다.")
    })
    @ApiOperation(value = "신고를 등록할 때 쓰는 메소드")
    @ApiImplicitParam(name = "course", value = "코스 리뷰를 등록한 메뉴", dataType = "CourseReviewVoReq")
    @PostMapping("")
    public ResponseEntity<String> createReport(@Valid @RequestBody CreateReportRequest request) {
        reportService.createReport(request);
        return ResponseEntity.ok().body("신고가 등록 되었습니다.");
    }

    @GetMapping("")
    public ResponseEntity<?> readMyReport(@RequestParam(value = "filter", required = false) String filter,
                                          Pageable pageable) {

        if(String.valueOf(filter).equals("auth")) {
            return ResponseEntity.status(200).body(reportService.readMyReport(pageable));
        } else {
            return ResponseEntity.ok().body(reportService.readAll());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReport(@PathVariable Long id,
                                               @Valid @RequestBody UpdateReportRequest request){
        reportService.updateReport(id, request);
        return ResponseEntity.ok().body("신고가 수정 되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id){
        reportService.deleteReport(id);
        return ResponseEntity.ok().body("신고가 삭제 되었습니다.");
    }

}
