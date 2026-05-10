package com.sovereign.service;

import org.springframework.stereotype.Service;

import com.sovereign.model.Inquiry;

@Service
public class ValidationService {

    public boolean validateInquiry(Inquiry inquiry){
        if(inquiry.getName() == null || inquiry.getName().isEmpty()){
            return false;
        }
        if(inquiry.getEmail() == null || inquiry.getEmail().isEmpty()){
            return false;
        }
        if(inquiry.getCategory() == null || inquiry.getCategory().isEmpty()){
            return false;
        }
        if(inquiry.getMessage() == null || inquiry.getMessage().isEmpty()){
            return false;
        }
        return true;
    }
}
