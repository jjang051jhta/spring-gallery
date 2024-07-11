package com.jjang051.gallery.controller;

import com.jjang051.gallery.dto.GalleryDto;
import com.jjang051.gallery.enums.Category;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

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

        return "redirect:/";
    }
}
