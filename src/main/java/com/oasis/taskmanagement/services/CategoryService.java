package com.oasis.taskmanagement.services;

import com.oasis.taskmanagement.dtos.requestDtos.TaskCategoryRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.TaskCategoryResponseDto;

import java.util.List;

public interface CategoryService {
    ApiResponse<TaskCategoryResponseDto> createCategory(TaskCategoryRequestDto categoryRequestDto);

    ApiResponse<List<TaskCategoryResponseDto>> listAll();
}
