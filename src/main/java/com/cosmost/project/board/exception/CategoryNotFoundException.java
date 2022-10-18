package com.cosmost.project.board.exception;

public class CategoryNotFoundException extends RuntimeException {

    private static final String MESSAGE = "카테고리가 존재하지 않습니다.";

    public  CategoryNotFoundException() {
        super(MESSAGE);
    }
}
