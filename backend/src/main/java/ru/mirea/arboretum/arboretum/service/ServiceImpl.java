package ru.mirea.arboretum.arboretum.service;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.arboretum.arboretum.dto.DTOConverter;
import ru.mirea.arboretum.arboretum.models.Bucket;
import ru.mirea.arboretum.arboretum.models.Param;
import ru.mirea.arboretum.arboretum.models.Plant;
import ru.mirea.arboretum.arboretum.models.User;
import ru.mirea.arboretum.arboretum.repo.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service("ServiceImpl")
public class ServiceImpl implements Service {

    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final StatusRepository statusRepository;
    private final ParamRepository paramRepository;
    private final BucketRepository bucketRepository;

    public ServiceImpl(UserRepository userRepository, PlantRepository plantRepository, StatusRepository statusRepository, ParamRepository paramRepository, BucketRepository bucketRepository) {
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
        this.statusRepository = statusRepository;
        this.paramRepository = paramRepository;
        this.bucketRepository = bucketRepository;
    }


    @Override
    public ResponseEntity<?> saveUser(User user) {
        User newUser = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(newUser);
        return new ResponseEntity<>(newUser.getUserId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return new ResponseEntity<>(user.get().getUserId(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> plantInfo(Long planId) {
        Optional<Plant> plant = plantRepository.findById(planId);
        if(plant.isPresent()){
            List<Param> param = paramRepository.findForPlan(planId);
            if(!param.isEmpty()){
                DTOConverter converter = DTOConverter.getInstance();
                return new ResponseEntity<>(converter.convertToPlantWithParam(plant.get(), param), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(plant.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> myPlant(Long userId) {
        List<Plant> myPlants = statusRepository.findMyPlant(userId);
        if(!myPlants.isEmpty()){
            return new ResponseEntity<>(myPlants, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<?> loadPicture(MultipartFile file, Long plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        if(plant.isPresent()) {
            try {
                Bucket bucket = bucketRepository.findForPlant(plantId);
                if (bucket == null) {
                    MinioController.createBucket(plantId);
                    bucket = Bucket.builder()
                            .plant(plant.get())
                            .bucketId(plantId)
                            .build();
                    bucketRepository.save(bucket);
                }
                MinioController.loadImage(plantId, getFile(file));
                return new ResponseEntity<>(plant.get(), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getPicture(Long plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        if(plant.isPresent()) {
            try {
                Bucket bucket = bucketRepository.findForPlant(plantId);
                if (bucket == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                List<File> images = MinioController.getImages(plantId);
                if(!images.isEmpty()) {
                    return new ResponseEntity<>(images, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private File getFile(MultipartFile multiFile){
        String fileName = multiFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
