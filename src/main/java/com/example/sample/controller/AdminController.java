package com.example.sample.controller;

import com.example.sample.entity.Info;
import com.example.sample.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final InfoService infoService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("infoList", infoService.getAllInfo());
        model.addAttribute("totalCount", infoService.getAllInfo().size());
        return "admin/dashboard";
    }

    @GetMapping("/info/create")
    public String createForm() {
        return "admin/form";
    }

    @PostMapping("/info/create")
    public String create(@ModelAttribute Info info) {
        infoService.saveInfo(info);
        return "redirect:/admin";
    }

    @GetMapping("/info/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Info info = infoService.getInfoById(id);
        if (info == null) {
            return "redirect:/admin";
        }
        model.addAttribute("info", info);
        return "admin/edit";
    }

    @PostMapping("/info/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute Info info) {
        Info existingInfo = infoService.getInfoById(id);
        if (existingInfo != null) {
            existingInfo.setTitle(info.getTitle());
            existingInfo.setContent(info.getContent());
            infoService.saveInfo(existingInfo);
        }
        return "redirect:/admin";
    }

    @PostMapping("/info/{id}/delete")
    public String delete(@PathVariable Long id) {
        infoService.deleteInfo(id);
        return "redirect:/admin";
    }
}
