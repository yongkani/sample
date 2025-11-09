package com.example.sample.controller;

import com.example.sample.entity.Gallery;
import com.example.sample.entity.Info;
import com.example.sample.service.FileStorageService;
import com.example.sample.service.GalleryService;
import com.example.sample.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final InfoService infoService;
    private final GalleryService galleryService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("infoList", infoService.getAllInfo());
        model.addAttribute("galleryList", galleryService.getAllGalleries());
        model.addAttribute("infoCount", infoService.getAllInfo().size());
        model.addAttribute("galleryCount", galleryService.getAllGalleries().size());
        return "admin/dashboard";
    }

    // Info 관리
    @GetMapping("/info/create")
    public String createInfoForm() {
        return "admin/form";
    }

    @PostMapping("/info/create")
    public String createInfo(@ModelAttribute Info info) {
        infoService.saveInfo(info);
        return "redirect:/admin";
    }

    @GetMapping("/info/{id}/edit")
    public String editInfoForm(@PathVariable Long id, Model model) {
        Info info = infoService.getInfoById(id);
        if (info == null) {
            return "redirect:/admin";
        }
        model.addAttribute("info", info);
        return "admin/edit";
    }

    @PostMapping("/info/{id}/edit")
    public String editInfo(@PathVariable Long id, @ModelAttribute Info info) {
        Info existingInfo = infoService.getInfoById(id);
        if (existingInfo != null) {
            existingInfo.setTitle(info.getTitle());
            existingInfo.setContent(info.getContent());
            infoService.saveInfo(existingInfo);
        }
        return "redirect:/admin";
    }

    @PostMapping("/info/{id}/delete")
    public String deleteInfo(@PathVariable Long id) {
        infoService.deleteInfo(id);
        return "redirect:/admin";
    }

    // Gallery 관리
    @GetMapping("/gallery/create")
    public String createGalleryForm() {
        return "admin/gallery-form";
    }

    @PostMapping("/gallery/create")
    public String createGallery(@RequestParam("title") String title,
                                 @RequestParam(value = "description", required = false) String description,
                                 @RequestParam(value = "displayOrder", defaultValue = "0") Integer displayOrder,
                                 @RequestParam("imageFile") MultipartFile imageFile) {
        
        // 파일 저장
        String imageUrl = fileStorageService.storeFile(imageFile);
        
        // Gallery 객체 생성 및 저장
        Gallery gallery = Gallery.builder()
                .title(title)
                .description(description)
                .imageUrl(imageUrl)
                .displayOrder(displayOrder)
                .build();
        
        galleryService.saveGallery(gallery);
        return "redirect:/admin";
    }

    @GetMapping("/gallery/{id}/edit")
    public String editGalleryForm(@PathVariable Long id, Model model) {
        Gallery gallery = galleryService.getGalleryById(id);
        if (gallery == null) {
            return "redirect:/admin";
        }
        model.addAttribute("gallery", gallery);
        return "admin/gallery-edit";
    }

    @PostMapping("/gallery/{id}/edit")
    public String editGallery(@PathVariable Long id,
                               @RequestParam("title") String title,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam(value = "displayOrder", defaultValue = "0") Integer displayOrder,
                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        
        Gallery existingGallery = galleryService.getGalleryById(id);
        if (existingGallery != null) {
            existingGallery.setTitle(title);
            existingGallery.setDescription(description);
            existingGallery.setDisplayOrder(displayOrder);
            
            // 새 이미지 파일이 업로드된 경우
            if (imageFile != null && !imageFile.isEmpty()) {
                // 기존 파일 삭제
                fileStorageService.deleteFile(existingGallery.getImageUrl());
                // 새 파일 저장
                String newImageUrl = fileStorageService.storeFile(imageFile);
                existingGallery.setImageUrl(newImageUrl);
            }
            
            galleryService.saveGallery(existingGallery);
        }
        return "redirect:/admin";
    }

    @PostMapping("/gallery/{id}/delete")
    public String deleteGallery(@PathVariable Long id) {
        Gallery gallery = galleryService.getGalleryById(id);
        if (gallery != null) {
            // 파일 삭제
            fileStorageService.deleteFile(gallery.getImageUrl());
            // DB에서 삭제
            galleryService.deleteGallery(id);
        }
        return "redirect:/admin";
    }
}
