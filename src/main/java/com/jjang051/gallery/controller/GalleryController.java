package com.jjang051.gallery.controller;

import com.jjang051.gallery.dto.GalleryDto;
import com.jjang051.gallery.enums.Category;
import com.jjang051.gallery.service.GalleryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {
    private final GalleryService galleryService;
    private Category categoryArray [] =
            {Category.PAINT,Category.PHOTO,Category.SKETCH};
    List<Category> categoryList = Arrays.asList(categoryArray);

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("galleryDto",new GalleryDto());
        return "gallery/write";
    }

    @PostMapping("/write")
    public String writeProcess(@Valid @ModelAttribute GalleryDto galleryDto,
                               BindingResult bindingResult,
                               Model model
                               ) throws FileNotFoundException {
        if(bindingResult.hasErrors()) {
            model.addAttribute("categoryList",categoryList);
            return "gallery/write";
        }
        if(galleryDto.getFile().isEmpty()) {
            bindingResult.addError(
                new FieldError
            ("fileError","file","이미지를 선택해주세요"));
            model.addAttribute("categoryList",categoryList);
            return "gallery/write";
        }
        //log.info("galleryDto.getFile().isEmpty()===", galleryDto.getFile().isEmpty());
        galleryService.insertGallery(galleryDto);
        return "redirect:/gallery/list";
    }

    @GetMapping("/json-list")
    @ResponseBody
    @CrossOrigin(origins = "*",methods = RequestMethod.GET)
    public List<GalleryDto> jsonList(Model model) {
        return galleryService.list();
    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("galleryList",galleryService.list());
        return "gallery/list";
    }

    @GetMapping("/list02")
    public String list02(Model model) {
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("galleryList",galleryService.list());
        return "gallery/list02";
    }
}
