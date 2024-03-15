package com.oasis.taskmanagement.dtos.requestDtos;

import com.oasis.taskmanagement.model.enums.ReminderStatus;

import java.time.OffsetDateTime;

public class ReminderRequestDto {
    private Long taskId;
    private String message;
    private ReminderStatus status;
    private OffsetDateTime time;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }
}
