package com.oasis.taskmanagement.dtos.requestDtos;

import com.oasis.taskmanagement.model.enums.Priority;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class TaskSearchRequest {

    private String name;
    private String category;
    private TaskStatus status;
    private Priority priority;
    private OffsetDateTime dueDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime from;
    private OffsetDateTime to;

}
