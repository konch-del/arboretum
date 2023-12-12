package ru.mirea.arboretum.arboretum.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.arboretum.arboretum.models.User;

public interface Service {

    ResponseEntity<?> saveUser(@RequestBody User user);

    ResponseEntity<?> login(@RequestParam String email, @RequestParam String password);

    ResponseEntity<?> plantInfo(@RequestParam Long planId);

    ResponseEntity<?> myPlant(@RequestParam Long userId);

    ResponseEntity<?> loadPicture(@RequestParam("file") MultipartFile file, @RequestParam Long plantId);

    @ResponseBody
    ResponseEntity<?> getPicture(@RequestParam Long plantId);
}
