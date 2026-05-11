package com.sovereign.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sovereign.model.Car;
import com.sovereign.model.Inquiry;
import com.sovereign.repository.CarRepository;
import com.sovereign.repository.InquiryRepository;

@Service
public class DatabaseService {

    private ValidationService validator;
    private final InquiryRepository inquiryRepo;
    private final CarRepository carRepo;

    public DatabaseService(InquiryRepository inquiryRepo, CarRepository carRepo, ValidationService validator){
        this.inquiryRepo = inquiryRepo;
        this.carRepo = carRepo;
        this.validator = validator;
    }

    public void addInquiry(Inquiry inquiry){

        if(!validator.validateInquiry(inquiry)){
            throw new RuntimeException("All fields must be filled.");
        }

        inquiryRepo.save(inquiry);

    }

    public List<Inquiry> listInquiries(){
        return inquiryRepo.findAll();
    }

    public Car findCarById(Long id){
        return carRepo.findById(id).orElse(null);
    }

    public void deleteInquiry(Long id){
        inquiryRepo.deleteById(id);
    }

}
