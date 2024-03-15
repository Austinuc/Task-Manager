package com.oasis.taskmanagement.dtos.requestDtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oasis.taskmanagement.model.enums.Priority;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import jakarta.validation.constraints.Pattern;

import java.time.OffsetDateTime;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
