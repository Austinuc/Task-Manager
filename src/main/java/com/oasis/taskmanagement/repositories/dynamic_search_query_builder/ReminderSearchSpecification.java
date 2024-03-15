package com.oasis.taskmanagement.repositories.dynamic_search_query_builder;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.TaskReminder;
import com.oasis.taskmanagement.repositories.TaskRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ReminderSearchSpecification {
    private final TaskRepository taskRepository;

    public ReminderSearchSpecification(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Specification<TaskReminder> createSearchSpecificationFrom(ReminderRequestDto reminderRequestDto) {
        Objects.requireNonNull(reminderRequestDto);
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (reminderRequestDto.getStatus() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), reminderRequestDto.getStatus()));
            }

            if (reminderRequestDto.getTime() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("time"), reminderRequestDto.getTime()));
            }

            if (reminderRequestDto.getTaskId() != null) {
                Optional<TaskEntity> taskEntity = taskRepository.findById(reminderRequestDto.getTaskId());
                if (taskEntity.isPresent()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("task"), taskEntity));
                }
            }

            return predicate;
        };
    }
}
