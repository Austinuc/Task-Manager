package com.oasis.taskmanagement.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oasis.taskmanagement.model.enums.Priority;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponseDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Long userId;
    private TaskStatus status;
    private Priority priority;
    @NotNull
    private OffsetDateTime dueDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
