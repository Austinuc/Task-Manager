package com.oasis.taskmanagement.services.impl;

import com.oasis.taskmanagement.dtos.requestDtos.TaskRequestDto;
import com.oasis.taskmanagement.dtos.requestDtos.TaskSearchRequest;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.TaskResponseDto;
import com.oasis.taskmanagement.exception.ResourceNotFoundException;
import com.oasis.taskmanagement.mappers.TaskMapper;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.UserEntity;
import com.oasis.taskmanagement.model.enums.Priority;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import com.oasis.taskmanagement.repositories.TaskCategoryRepository;
import com.oasis.taskmanagement.repositories.TaskRepository;
import com.oasis.taskmanagement.repositories.UserRepository;
import com.oasis.taskmanagement.repositories.dynamic_search_query_builder.TaskSearchSpecification;
import com.oasis.taskmanagement.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskCategoryRepository categoryRepository;
    private final TaskRepository taskRepository;
    private final TaskSearchSpecification taskSearchSpecification;

    private final Integer DEFAULT_MAX_RESULT = 10;

    public TaskServiceImpl(UserRepository userRepository, TaskCategoryRepository categoryRepository, TaskRepository taskRepository, TaskSearchSpecification taskSearchSpecification) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
        this.taskSearchSpecification = taskSearchSpecification;
    }

    @Override
    public ApiResponse<TaskResponseDto> createTask(Long userId, TaskRequestDto taskRequestDto) {

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            TaskCategoryEntity categoryEntity = categoryRepository.findByName(taskRequestDto.getCategory())
                    .orElse(null);

            if (categoryEntity == null) {
                categoryEntity = new TaskCategoryEntity();
                categoryEntity.setName(taskRequestDto.getCategory());
                categoryEntity = categoryRepository.save(categoryEntity);
            }
            if (taskRequestDto.getDueDate() == null) {
                OffsetDateTime now = OffsetDateTime.now(Clock.systemUTC());
                taskRequestDto.setDueDate(now.plusDays(7));
            }
            if (taskRequestDto.getStatus() == null) {
                taskRequestDto.setStatus(TaskStatus.PENDING);
            }
            if (taskRequestDto.getPriority() == null) {
                taskRequestDto.setPriority(Priority.LOW);
            }
            TaskEntity taskEntity = TaskMapper.INSTANCE.fromDtoToEntity(taskRequestDto);
            taskEntity.setUser(userEntity.get());
            taskEntity.setCategory(categoryEntity);

            return new ApiResponse<>(
                    "Task created successfully",
                    true,
                    TaskMapper.INSTANCE.fromEntityToDto(taskRepository.save(taskEntity))
            );
        }
        throw new ResourceNotFoundException("User does not exist");
    }

    @Override
    public ApiResponse<TaskResponseDto> updateTask(Long userId, TaskRequestDto taskRequestDto) {
        if (userRepository.existsById(userId)) {
            TaskEntity taskEntity = taskRepository.findById(taskRequestDto.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("Task does not exist"));

            if (updateTaskFields(taskEntity, taskRequestDto)) {
                return new ApiResponse<>(
                        "Task updated successfully",
                        true,
                        TaskMapper.INSTANCE.fromEntityToDto(taskRepository.save(taskEntity))
                );
            }
            return new ApiResponse<>(
                    "Nothing to update",
                    false,
                    TaskMapper.INSTANCE.fromEntityToDto(taskEntity)
            );

        }
        throw new ResourceNotFoundException("User does not exist");
    }

    @Override
    public ApiResponse<List<TaskResponseDto>> taskSearch(TaskSearchRequest taskSearchRequest, Integer offSet, Integer limit) {
        List<TaskEntity> taskEntities = taskRepository.findAll(
                taskSearchSpecification.createSearchSpecificationFrom(taskSearchRequest),
                offSet != null ? offSet : 0,
                limit != null ? limit : DEFAULT_MAX_RESULT);
        if (taskEntities != null && !taskEntities.isEmpty()) {
            return new ApiResponse<>(
                    "Successful",
                    true,
                    taskEntities.stream()
                            .map(TaskMapper.INSTANCE::fromEntityToDto)
                            .collect(Collectors.toList())
            );
        }

        return new ApiResponse<>(
                "Failed",
                false,
                Collections.emptyList()
        );

    }

    @Override
    public ApiResponse<String> deleteTask(Long userId, Long taskId) {
        if (userRepository.existsById(userId)) {
            taskRepository.deleteById(taskId);
            return new ApiResponse<>("Successful", true, null);
        }
        return new ApiResponse<>("Failed", false, "user does not exist");
    }

    private boolean updateTaskFields(TaskEntity taskEntity, TaskRequestDto taskRequestDto) {
        boolean updated = false;
        if (taskRequestDto.getCategory() != null && !taskEntity.getCategory().getName().equals(taskRequestDto.getCategory())) {
            TaskCategoryEntity categoryEntity = categoryRepository.findByName(taskRequestDto.getCategory())
                    .orElse(null);

            if (categoryEntity == null) {
                categoryEntity = new TaskCategoryEntity();
                categoryEntity.setName(taskRequestDto.getCategory());
                categoryEntity = categoryRepository.save(categoryEntity);
            }
            taskEntity.setCategory(categoryEntity);
            updated = true;
        }

        if (taskRequestDto.getName() != null && !taskEntity.getName().equals(taskRequestDto.getName())) {
            taskEntity.setName(taskRequestDto.getName());
            updated = true;
        }

        if (taskRequestDto.getStatus() != null && !taskEntity.getStatus().equals(taskRequestDto.getStatus())) {
            taskEntity.setStatus(taskRequestDto.getStatus());
            updated = true;
        }

        return updated;
    }
}
