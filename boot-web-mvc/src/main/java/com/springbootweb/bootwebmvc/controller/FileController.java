package com.springbootweb.bootwebmvc.controller;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

@Controller
public class FileController {

    @Autowired
    private ResourceLoader resourceLoader;

    private final String PATH = "classpath:";

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
    
    @GetMapping("file/{filename}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws Exception{

        Resource resource = resourceLoader.getResource(PATH + filename);
        File file = resource.getFile();

        String mediaType = new Tika().detect(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; file")
                .header(HttpHeaders.CONTENT_TYPE, mediaType)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                .body(resource);
    }
}
