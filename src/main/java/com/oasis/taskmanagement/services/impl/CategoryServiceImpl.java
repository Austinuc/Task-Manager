package com.oasis.taskmanagement.services.impl;

import com.oasis.taskmanagement.dtos.requestDtos.TaskCategoryRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.TaskCategoryResponseDto;
import com.oasis.taskmanagement.mappers.CategoryMapper;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import com.oasis.taskmanagement.repositories.TaskCategoryRepository;
import com.oasis.taskmanagement.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final TaskCategoryRepository categoryRepository;

    public CategoryServiceImpl(TaskCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ApiResponse<TaskCategoryResponseDto> createCategory(TaskCategoryRequestDto categoryRequestDto) {
        Objects.requireNonNull(categoryRequestDto);
        TaskCategoryEntity categoryEntity = new TaskCategoryEntity();
        categoryEntity.setName(categoryRequestDto.getName());
        categoryEntity.setDescription(categoryRequestDto.getDescription());
        return new ApiResponse<>(
                "Successful",
                true,
                CategoryMapper.INSTANCE.fromEntityToResponseDto(categoryRepository.save(categoryEntity))
        );
    }

    @Override
    public ApiResponse<List<TaskCategoryResponseDto>> listAll() {
        return new ApiResponse<>(
                "Successful",
                true,
                categoryRepository.findAll().stream()
                        .map(CategoryMapper.INSTANCE::fromEntityToResponseDto)
                        .collect(Collectors.toList())
        );
    }
}
