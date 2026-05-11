package com.sovereign.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sovereign.model.Car;
import com.sovereign.repository.CarRepository;

import jakarta.servlet.http.HttpSession;

// Since there is no landing page / list page with cars and favorite buttons yet this is just a template for future reference for when the pages are eventually added

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired //change this please
    private CarRepository carRepository;

    @GetMapping("/check")
    @ResponseBody
    public String getSession(HttpSession session) {
        String sessionId = session.getId();

        if (sessionId == null) {
            return "No sessions.";
        }

        return "Session found with ID: " + sessionId;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "Session deleted.";
    }

    // ------------------------------------------------------------

    @PostMapping("/favorite/{carId}")
    public String toggleFavorite(@PathVariable Long carId, HttpSession session){
        ArrayList<Long> favorites = (ArrayList<Long>)session.getAttribute("favorites");

        if(favorites == null){
            favorites = new ArrayList<>();
        }

        Car car = carRepository.findById(carId).orElse(null);

        if (car != null) {
            favorites.add(carId);
        }

        if(favorites.contains(carId)) {
            favorites.remove(carId);
        }
        else {
            favorites.add(carId);
        }

        session.setAttribute("favorites", favorites);
        return "Favorite Added/Removed.";
    }

    @GetMapping("/showlist")
    public String showList(HttpSession session, Model model){
        ArrayList<Long> favorites = (ArrayList<Long>)session.getAttribute("favorites");

        if(favorites == null || favorites.isEmpty()){
            model.addAttribute("errormsg", "No favorites.");
            return "error";
        }

        model.addAttribute("list", favorites);
        return "list"; //this page does not exist but this is where it would display the users favorites using thymeleaf
    }

    @GetMapping("/clearlist")
    @ResponseBody
    public String clearList(HttpSession session){
        ArrayList<Long> favorites = (ArrayList<Long>)session.getAttribute("favorites");

        if(favorites == null || favorites.isEmpty()){
            return "No favorites.";
        }

        favorites.clear();
        session.setAttribute("favorites", favorites);
        return "List cleared.";
    }
}
