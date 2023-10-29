package ru.mirea.arboretum.arboretum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.arboretum.arboretum.models.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
