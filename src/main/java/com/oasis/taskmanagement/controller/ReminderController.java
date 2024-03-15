package com.oasis.taskmanagement.controller;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.ReminderResponseDto;
import com.oasis.taskmanagement.services.ReminderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('task_write')")
    public ResponseEntity<ApiResponse<ReminderResponseDto>> createReminder(@RequestBody ReminderRequestDto reminderRequestDto) {
        return ResponseEntity.ok(reminderService.createReminder(reminderRequestDto));
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('task_read')")
    public ResponseEntity<ApiResponse<List<ReminderResponseDto>>> searchReminder(@RequestBody ReminderRequestDto reminderRequestDto) {
        return ResponseEntity.ok(reminderService.reminderSearch(reminderRequestDto));
    }
}
