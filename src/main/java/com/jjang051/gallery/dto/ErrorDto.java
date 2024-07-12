package com.jjang051.gallery.dto;


import com.jjang051.gallery.code.ErrorCode;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private ErrorCode errorCode;
    private String errorMessage;
}
