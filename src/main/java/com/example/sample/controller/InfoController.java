package com.example.sample.controller;

import com.example.sample.entity.Info;
import com.example.sample.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        int size = 5; // 5개씩 페이징
        Page<com.example.sample.entity.Info> infoPage = infoService.getInfoPage(page, size);
        model.addAttribute("infoPage", infoPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "info/list";
    }

    @GetMapping("/create")
    public String createForm() {
        return "info/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Info info) {
        infoService.saveInfo(info);
        return "redirect:/info";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("info", infoService.getInfoById(id));
        return "info/detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        infoService.deleteInfo(id);
        return "redirect:/info";
    }
}
