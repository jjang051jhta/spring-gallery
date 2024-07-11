package com.jjang051.gallery.service;

import com.jjang051.gallery.dao.GalleryDao;
import com.jjang051.gallery.dto.GalleryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryService {
    private final GalleryDao galleryDao;

    public int insertGallery(GalleryDto galleryDto) {
        // 이름변경   aaa.jpg
        // aaa_20240711113124.jpg
        // 폴더에다가 파일을 옮기기
        log.info("galleryDto==={}",galleryDto);
        log.info("galleryDto.getFile()==={}",galleryDto.getFile().toString());
        log.info("galleryDto.getFile()==={}",galleryDto.getFile().getOriginalFilename());




        return galleryDao.insertGallery(galleryDto);
    }
}
