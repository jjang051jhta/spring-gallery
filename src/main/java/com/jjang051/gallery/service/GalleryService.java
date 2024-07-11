package com.jjang051.gallery.service;

import com.jjang051.gallery.dao.GalleryDao;
import com.jjang051.gallery.dto.GalleryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryService {

    @Value("${file.path}")
    private String uploadFolder;

    private final GalleryDao galleryDao;
    public List<GalleryDto> list() {
        return galleryDao.getAllList();
    }

    public int insertGallery(GalleryDto galleryDto)  {
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
        log.info("renameFileName==={},uploadFolder==={}",renameFileName,uploadFolder);
        Path path = Paths.get(uploadFolder+ File.separator+renameFileName);
        try {
            Files.write(path,galleryDto.getFile().getBytes());
            //galleryDto.getFile().transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GalleryDto sendGalleryDto =  GalleryDto.builder()
                .title(galleryDto.getTitle())
                .desc(galleryDto.getDesc())
                .category(galleryDto.getCategory())
                .point(galleryDto.getPoint())
                .originalTitle(originalFileName)
                .renameTitle(renameFileName)
                .build();

        return galleryDao.insertGallery(sendGalleryDto);
    }
}
