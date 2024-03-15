package com.oasis.taskmanagement.services;

import com.oasis.taskmanagement.dtos.requestDtos.TaskRequestDto;
import com.oasis.taskmanagement.dtos.requestDtos.TaskSearchRequest;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.TaskResponseDto;

import java.util.List;

public interface TaskService {

    ApiResponse<TaskResponseDto> createTask(Long userId, TaskRequestDto taskRequestDto);
    ApiResponse<TaskResponseDto> updateTask(Long userId, TaskRequestDto taskRequestDto);
    ApiResponse<List<TaskResponseDto>> taskSearch(TaskSearchRequest taskSearchRequest, Integer offSet, Integer limit);
    ApiResponse<String> deleteTask(Long userId, Long taskId);

}
