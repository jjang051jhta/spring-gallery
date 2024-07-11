package com.jjang051.gallery.service;

import com.jjang051.gallery.dao.GalleryDao;
import com.jjang051.gallery.dto.GalleryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        String originalFileName = galleryDto.getFile().getOriginalFilename();
        String fileName =
                originalFileName.substring(0,originalFileName.lastIndexOf("."));
        String extention =
                originalFileName.substring(originalFileName.lastIndexOf("."));
        log.info("fileName=={}, extention==={}",fileName,extention);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("YYYYMMddhhmmss");
        String format = now.format(dateTimeFormatter);  //fileName _ 20240711115021 extention
        String renameFileName = fileName+"_"+format+extention;
        log.info("renameFileName==={}",renameFileName);


        return 1;
        //return galleryDao.insertGallery(galleryDto);
    }
}
