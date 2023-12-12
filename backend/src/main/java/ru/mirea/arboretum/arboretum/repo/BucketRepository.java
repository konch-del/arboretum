package ru.mirea.arboretum.arboretum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.arboretum.arboretum.models.Bucket;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Long> {

    @Query(value = "SELECT * FROM bucket WHERE plantId = ?1", nativeQuery = true)
    Bucket findForPlant(Long plant);
}
