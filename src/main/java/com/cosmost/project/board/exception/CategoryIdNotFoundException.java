package com.cosmost.project.board.exception;

public class CategoryIdNotFoundException extends RuntimeException {

    private static final String MESSAGE = "카테고리ID가 존재하지 않습니다.";

    public CategoryIdNotFoundException(){
        super(MESSAGE);
    }
}
