package com.jjang051.gallery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class GalleryDto {
    private int id;

    @NotBlank(message = "제목은 필수입력사항입니다.")
    private String title;

    @NotBlank(message = "제목은 필수입력사항입니다.")
    @Size(min = 10,max = 1000, message = "10~1000자 사이로 써주세요.")
    private String desc;

    @Size(min = 0,max = 5, message = "0~5점 사이로 입력해주세요.")
    private Double point;

    @NotBlank(message = "카테고리는 필수입력사항입니다.")
    private String category;

    private MultipartFile file;

    private String originalTitle;

    private String renameTitle;

    private LocalDateTime regDate;

    @Builder
    public GalleryDto(String title, String desc, Double point, String category, MultipartFile file, String originalTitle) {
        this.title = title;
        this.desc = desc;
        this.point = point;
        this.category = category;
        this.file = file;
        this.originalTitle = originalTitle;
    }
}
