package com.oasis.taskmanagement.repositories;

import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.repositories.custom.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskEntity> {
}
