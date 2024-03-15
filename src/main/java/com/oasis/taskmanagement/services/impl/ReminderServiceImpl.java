package com.oasis.taskmanagement.services.impl;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.ReminderResponseDto;
import com.oasis.taskmanagement.exception.ResourceNotFoundException;
import com.oasis.taskmanagement.mappers.ReminderMapper;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.TaskReminder;
import com.oasis.taskmanagement.model.enums.ReminderStatus;
import com.oasis.taskmanagement.repositories.ReminderRepository;
import com.oasis.taskmanagement.repositories.TaskRepository;
import com.oasis.taskmanagement.repositories.dynamic_search_query_builder.ReminderSearchSpecification;
import com.oasis.taskmanagement.services.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.KeyValuePair;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReminderServiceImpl implements ReminderService {
    private final static Logger logger = LoggerFactory.getLogger(ReminderServiceImpl.class);

    private final TaskRepository taskRepository;
    private final ReminderRepository reminderRepository;
    private final ReminderSearchSpecification reminderSearchSpecification;

    public ReminderServiceImpl(TaskRepository taskRepository, ReminderRepository reminderRepository, ReminderSearchSpecification reminderSearchSpecification) {
        this.taskRepository = taskRepository;
        this.reminderRepository = reminderRepository;
        this.reminderSearchSpecification = reminderSearchSpecification;
    }

    @Override
    public ApiResponse<ReminderResponseDto> createReminder(ReminderRequestDto reminderRequestDto) {
        Objects.requireNonNull(reminderRequestDto);
        TaskEntity taskEntity = taskRepository.findById(reminderRequestDto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist for reminder"));
        if (reminderRequestDto.getStatus() == null) {
            reminderRequestDto.setStatus(ReminderStatus.ACTIVE);
        }
        if (reminderRequestDto.getTime() == null) {
            OffsetDateTime now = OffsetDateTime.now(Clock.systemUTC());
            reminderRequestDto.setTime(now.plusDays(2L));
        }
        TaskReminder taskReminder = ReminderMapper.INSTANCE.fromRequestToEntity(reminderRequestDto);
        taskReminder.setTask(taskEntity);
        return new ApiResponse<>(
                "Successful",
                true,
                ReminderMapper.INSTANCE.fromEntityToResponse(reminderRepository.save(taskReminder)));
    }

    @Override
    public ApiResponse<List<ReminderResponseDto>> reminderSearch(ReminderRequestDto reminderRequestDto) {
        if (reminderRequestDto != null) {

            List<TaskReminder> reminders = reminderRepository.findAll(
                    reminderSearchSpecification.createSearchSpecificationFrom(reminderRequestDto),
                    0,
                    50);
            if (reminders != null && !reminders.isEmpty()) {
                return new ApiResponse<>(
                        "Successful",
                        true,
                        reminders.stream()
                                .map(ReminderMapper.INSTANCE::fromEntityToResponse)
                                .collect(Collectors.toList())
                        );
            }

        }
        return new ApiResponse<>(
                "Failed",
                false,
                Collections.emptyList()
        );
    }

    @Override
    public void removeReminder(TaskReminder reminder) {
        Objects.requireNonNull(reminder);
        Objects.requireNonNull(reminder.getId());

        reminderRepository.delete(reminder);
    }

    @Override
    public void sendReminder(String email, String message) {
        logger.info(message, new KeyValuePair("email", email));
    }
}
