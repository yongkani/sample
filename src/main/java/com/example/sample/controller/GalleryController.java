package com.example.sample.controller;

import com.example.sample.entity.Gallery;
import com.example.sample.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping
    public String galleryList(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 12); // 한 페이지에 12개씩
        Page<Gallery> galleryPage = galleryService.getAllGalleries(pageable);
        
        model.addAttribute("galleryPage", galleryPage);
        model.addAttribute("currentPage", page);
        return "gallery/list";
    }

    @GetMapping("/{id}")
    public String galleryDetail(@PathVariable Long id, Model model) {
        Gallery gallery = galleryService.getGalleryById(id);
        if (gallery == null) {
            return "redirect:/gallery";
        }
        model.addAttribute("gallery", gallery);
        return "gallery/detail";
    }
}
