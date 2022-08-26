package com.springbootweb.bootwebmvc.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String sampleErrorHandler(RuntimeException exception, Model model){
        model.addAttribute("message", "Runtime Message");
        return "error/error";
    }

    @InitBinder()
    public void initSampleBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("categories")
    public List<String> categories(){

        List<String> categories = new ArrayList<>();
        categories.add("study");
        categories.add("book");
        categories.add("seminar");

        return categories;
    }
}
