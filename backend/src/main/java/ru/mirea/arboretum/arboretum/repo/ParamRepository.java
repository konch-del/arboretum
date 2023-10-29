package ru.mirea.arboretum.arboretum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.arboretum.arboretum.models.Param;

import java.util.List;

public interface ParamRepository extends JpaRepository<Param, Long> {

    @Query(value = "SELECT * FROM params WHERE plantId = ?1", nativeQuery = true)
    List<Param> findForPlan(Long plant);
}
