package com.sovereign.controller;

import com.sovereign.model.Inquiry;
import com.sovereign.repository.InquiryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("inquiry", new Inquiry());
        return "inquiry-form";
    }

    @PostMapping
    public String submitInquiry(@Valid @ModelAttribute Inquiry inquiry,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "inquiry-form";
        }

        inquiryRepository.save(inquiry);
        return "redirect:/inquiry/submitted";
    }

    @GetMapping("/submitted")
    public String submitted() {
        return "inquiry-submitted";
    }
}
