package com.oasis.taskmanagement.repositories;

import com.oasis.taskmanagement.model.TaskCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategoryEntity, Long> {
    Optional<TaskCategoryEntity> findByName(String categoryName);

    boolean existsByName(String categoryName);
}
