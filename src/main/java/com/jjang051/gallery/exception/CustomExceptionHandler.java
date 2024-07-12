package com.jjang051.gallery.exception;


import com.jjang051.gallery.dto.ErrorDto;
import com.jjang051.gallery.dto.GalleryDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;

//@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({RuntimeException.class, NullPointerException.class})
    public String runtimeHandling() {
        return "error/5xx";
    }

    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public ErrorDto memberExceptionHandling(MemberException e, Model model) {

        ErrorDto errorDto =
                ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getErrorCode().getMessage())
                .build();
        //model.addAttribute("errorDto",errorDto);
        return errorDto;
    }

    @ExceptionHandler(BadRequestException.class)
    //@ResponseBody
    public String badRequestExceptionHandling(BadRequestException e,  Model model) {
        ErrorDto errorDto =
                ErrorDto.builder()
                        .errorCode(e.getErrorCode())
                        .errorMessage(e.getErrorCode().getMessage())
                        .build();
//        bindingResult
//                .reject("loginFail", "패스워드 확인해 주세요.");
        model.addAttribute("errorDto",errorDto);
        return "member/info-pass";
    }


    @ExceptionHandler(FileNotFoundException.class)
    //@ResponseBody
    public String fileNotFoundException(FileNotFoundException e, Model model) {
        ErrorDto errorDto =
                ErrorDto.builder()
                        .errorCode(new ErrorDto().getErrorCode())
                        .build();
//        log.info("errorDto==={}",errorDto.getErrorMessage());
//        bindingResult
//                .reject("loginFail", "패스워드 확인해 주세요.");
        model.addAttribute("errorDto",errorDto);
        model.addAttribute("galleryDto",new GalleryDto());
        return "gallery/write";
    }



}
