package com.cosmost.project.board.exception;

public class ReportIdNotFoundException extends RuntimeException {
    private static final String MESSAGE = "작성한 신고가 존재하지 않습니다.";

    public  ReportIdNotFoundException() {
        super(MESSAGE);
    }

}
