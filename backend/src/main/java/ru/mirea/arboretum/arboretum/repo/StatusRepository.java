package ru.mirea.arboretum.arboretum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.arboretum.arboretum.models.Plant;
import ru.mirea.arboretum.arboretum.models.PlantStatus;

import java.util.List;

public interface StatusRepository extends JpaRepository<PlantStatus, Long> {

    @Query(value = "SELECT * " +
            "FROM plantStatus ps," +
            "plant p" +
            "WHERE p.plantId = ps.plantId AND ps.userId = ?1", nativeQuery = true)
    List<Plant> findMyPlant(Long userId);
}
