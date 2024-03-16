package com.oasis.taskmanagement.dtos.requestDtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oasis.taskmanagement.model.enums.Priority;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestDto {
    private Long taskId;
    @Pattern(regexp = "[a-zA-Z_0-9\\s-]{4,}")
    private String name;
    private String description;
    @Pattern(regexp = "[a-zA-Z_\\s-]{4,}")
    private String category;
    private TaskStatus status;

    private Priority priority;

    private OffsetDateTime dueDate;
}
