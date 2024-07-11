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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
                               ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("categoryList",categoryList);
            return "gallery/write";
        }
        log.info("galleryDto==={}",galleryDto.toString());
        int result = galleryService.insertGallery(galleryDto);
        return "redirect:/";
    }

    @GetMapping("/json-list")
    @ResponseBody
    public List<GalleryDto> jsonList(Model model) {
        return galleryService.list();
    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("galleryList",galleryService.list());
        return "gallery/list";
    }
}
