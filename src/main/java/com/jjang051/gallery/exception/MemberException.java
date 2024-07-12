package com.jjang051.gallery.exception;


import com.jjang051.gallery.code.ErrorCode;
import lombok.Getter;

@Getter

public class MemberException extends RuntimeException {
    private ErrorCode errorCode;
    private String detailMessage;

    public MemberException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MemberException(ErrorCode errorCode, String detailMessage) {
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
