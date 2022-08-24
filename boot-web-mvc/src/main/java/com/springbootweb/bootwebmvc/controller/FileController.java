package com.springbootweb.bootwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

    @GetMapping("file")
    public String fileForm(Model model){
        return "file/form-file";
    }

    @PostMapping("file")
    public String uploadFile(@RequestParam MultipartFile file,
                             RedirectAttributes redirectAttributes){

        String message = file.getOriginalFilename() + " is uploaded";
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/file";
    }
}
