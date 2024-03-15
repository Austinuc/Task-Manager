package com.oasis.taskmanagement.repositories;

import com.oasis.taskmanagement.model.TaskReminder;
import com.oasis.taskmanagement.repositories.custom.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends BaseRepository<TaskReminder, Long>, JpaSpecificationExecutor<TaskReminder> {
}
