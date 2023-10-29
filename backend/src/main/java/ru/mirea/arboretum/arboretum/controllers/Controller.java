package ru.mirea.arboretum.arboretum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.arboretum.arboretum.models.User;
import ru.mirea.arboretum.arboretum.service.Service;

@RestController
public class Controller {

    private final Service service;

    public Controller(Service service){
        this.service = service;
    }

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        return service.login(email, password);
    }

    @GetMapping("/plantInfo")
    public ResponseEntity<?> plantInfo(@RequestParam Long planId){
        return service.plantInfo(planId);
    }

    @GetMapping("/myplant")
    public ResponseEntity<?> myPlant(@RequestParam Long userId){
        return service.myPlant(userId);
    }

}
