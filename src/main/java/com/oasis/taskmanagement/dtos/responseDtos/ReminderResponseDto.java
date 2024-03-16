package com.oasis.taskmanagement.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oasis.taskmanagement.model.enums.ReminderStatus;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReminderResponseDto {

    private Long id;
    private Long taskId;
    private String message;
    private ReminderStatus status;
    private OffsetDateTime time;

}
