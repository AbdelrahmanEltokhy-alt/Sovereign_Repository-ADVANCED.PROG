package com.sovereign.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sovereign.model.Car;
import com.sovereign.repository.CarRepository;

@Service
public class DatabaseService {

    private final CarRepository carRepo;

    public DatabaseService(CarRepository carRepo){
        this.carRepo = carRepo;
    }


    public Car findCarById(Long id){
        return carRepo.findById(id).orElse(null);
    }

}
