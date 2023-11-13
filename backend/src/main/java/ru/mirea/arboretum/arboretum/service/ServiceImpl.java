package ru.mirea.arboretum.arboretum.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.mirea.arboretum.arboretum.dto.DTOConverter;
import ru.mirea.arboretum.arboretum.dto.PlantWithParam;
import ru.mirea.arboretum.arboretum.models.Param;
import ru.mirea.arboretum.arboretum.models.Plant;
import ru.mirea.arboretum.arboretum.models.User;
import ru.mirea.arboretum.arboretum.repo.ParamRepository;
import ru.mirea.arboretum.arboretum.repo.PlantRepository;
import ru.mirea.arboretum.arboretum.repo.StatusRepository;
import ru.mirea.arboretum.arboretum.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service("ServiceImpl")
public class ServiceImpl implements Service {

    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final StatusRepository statusRepository;
    private final ParamRepository paramRepository;

    public ServiceImpl(UserRepository userRepository, PlantRepository plantRepository, StatusRepository statusRepository, ParamRepository paramRepository) {
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
        this.statusRepository = statusRepository;
        this.paramRepository = paramRepository;
    }


    @Override
    public ResponseEntity<?> saveUser(User user) {
        User newUser = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return new ResponseEntity<>(HttpStatus.OK);
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
        if(myPlants.isEmpty()){
            return new ResponseEntity<>(myPlants, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
