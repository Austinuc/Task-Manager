package com.oasis.taskmanagement.controller;

import com.oasis.taskmanagement.dtos.requestDtos.TaskRequestDto;
import com.oasis.taskmanagement.dtos.requestDtos.TaskSearchRequest;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.TaskResponseDto;
import com.oasis.taskmanagement.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Creates a new task for a given user",
            description = "UserId must be valid")
    @PreAuthorize("hasAnyAuthority('task_write')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(
            @RequestBody @Valid TaskRequestDto taskRequestDto,
            @RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok().body(taskService.createTask(userId, taskRequestDto));
    }

    @Operation(summary = "Updates the given task for a user",
            description = "Email must be unique")
    @PreAuthorize("hasAnyAuthority('task_write')")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTask(
            @RequestBody @Valid TaskRequestDto taskRequestDto,
            @RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok().body(taskService.updateTask(userId, taskRequestDto));
    }

    @Operation(summary = "Perform a dynamic AND search for tasks based on provided inputs")
    @PreAuthorize("hasAnyAuthority('task_read')")
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> searchTask(
            @RequestBody @Valid TaskSearchRequest taskSearchRequest,
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "limit") Integer limit) {
        return ResponseEntity.ok().body(taskService.taskSearch(taskSearchRequest, offset, limit));
    }

    @Operation(summary = "Deletes a task for a given user")
    @PreAuthorize("hasAnyAuthority('task_delete')")
    @DeleteMapping("/{taskId}/delete/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteTask(
            @PathVariable Long taskId,
            @PathVariable Long userId) {
        return ResponseEntity.ok().body(taskService.deleteTask(taskId, userId));
    }
}
