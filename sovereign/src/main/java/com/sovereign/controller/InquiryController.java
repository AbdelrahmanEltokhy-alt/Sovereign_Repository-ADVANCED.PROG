package com.sovereign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sovereign.model.Inquiry;
import com.sovereign.service.DatabaseService;
import com.sovereign.service.ValidationService;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private ValidationService validator;
    private final DatabaseService dbs;

    InquiryController(DatabaseService dbs, ValidationService validator){
        this.dbs = dbs;
        this.validator = validator;
    }

    @GetMapping("/")
    public String loadForm(){
        return "inquiry-form";
    }

    @PostMapping("/submit")
    public String sendInquiry(@ModelAttribute Inquiry inquiry, Model model){
        if(!validator.validateInquiry(inquiry)){
            model.addAttribute("errormsg", "Could not validate form entry");
            return "error";
        }
        else{
            dbs.addInquiry(inquiry);
            model.addAttribute("inquiry", inquiry);
            return "inquiry-submitted";
        }
    }

}
