package com.jjang051.gallery.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    PAINT("paint","페인트"),
    PHOTO("photo","포토"),
    SKETCH("sketch","스케치");

    private final String eng;
    private final String kor;

//    Category(String eng, String kor) {
//        this.eng = eng;
//        this.kor = kor;
//    }
}
