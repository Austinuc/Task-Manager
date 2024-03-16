package com.oasis.taskmanagement.dtos.requestDtos;

import com.oasis.taskmanagement.model.enums.ReminderStatus;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReminderRequestDto {
    private Long taskId;
    private String message;
    private ReminderStatus status;
    private OffsetDateTime time;
}
