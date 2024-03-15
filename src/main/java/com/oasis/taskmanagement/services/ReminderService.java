package com.oasis.taskmanagement.services;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.ReminderResponseDto;
import com.oasis.taskmanagement.model.TaskReminder;

import java.util.List;

public interface ReminderService {

    ApiResponse<ReminderResponseDto> createReminder(ReminderRequestDto reminderRequestDto);

    ApiResponse<List<ReminderResponseDto>> reminderSearch(ReminderRequestDto reminderRequestDto);
    void removeReminder(TaskReminder reminder);
    void sendReminder(String email, String message);
}
