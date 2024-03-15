package com.oasis.taskmanagement.repositories.dynamic_search_query_builder;


import com.oasis.taskmanagement.dtos.requestDtos.TaskSearchRequest;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.repositories.TaskCategoryRepository;
import com.oasis.taskmanagement.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;


@Service
public class TaskSearchSpecification {

    private final TaskCategoryRepository taskCategoryRepository;
    private final UserRepository userRepository;

    public TaskSearchSpecification(TaskCategoryRepository taskCategoryRepository, UserRepository userRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
        this.userRepository = userRepository;
    }

    public Specification<TaskEntity> createSearchSpecificationFrom(TaskSearchRequest taskSearchRequest) {
        Objects.requireNonNull(taskSearchRequest);
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (taskSearchRequest.getName() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), taskSearchRequest.getName()));
            }

            if (taskSearchRequest.getCategory() != null) {
                Optional<TaskCategoryEntity> categoryEntity = taskCategoryRepository.findByName(taskSearchRequest.getCategory());
                if (categoryEntity.isPresent()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), categoryEntity.get()));
                }
            }

            if (taskSearchRequest.getStatus() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("status"), taskSearchRequest.getStatus().name()));
            }

            if (taskSearchRequest.getPriority() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("priority"), taskSearchRequest.getPriority().name()));
            }

            if (taskSearchRequest.getDueDate() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dueDate"), taskSearchRequest.getDueDate()));
            } else if (taskSearchRequest.getCreatedAt() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("createdAt"), taskSearchRequest.getCreatedAt()));
            } else if (taskSearchRequest.getFrom() != null && taskSearchRequest.getTo() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("createdAt"), taskSearchRequest.getFrom(), taskSearchRequest.getTo()));
            } else if (taskSearchRequest.getTo() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("createdAt"), OffsetDateTime.now(), taskSearchRequest.getTo()));
            } else if (taskSearchRequest.getFrom() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("createdAt"), taskSearchRequest.getFrom(), OffsetDateTime.now()));
            }
            return predicate;
        };
    }
}

