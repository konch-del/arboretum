package ru.mirea.arboretum.arboretum.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.arboretum.arboretum.models.User;
import ru.mirea.arboretum.arboretum.service.Service;

import java.io.IOException;

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

    @PostMapping("/loadPicture")
    public ResponseEntity<?> loadPicture(@RequestParam("file") MultipartFile file, @RequestParam Long plantId){
        return service.loadPicture(file, plantId);
    }

    @GetMapping(value = "/getPicture", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody ResponseEntity<?> pic(@RequestParam Long plantId){
        return service.getPicture(plantId);
    }

}
