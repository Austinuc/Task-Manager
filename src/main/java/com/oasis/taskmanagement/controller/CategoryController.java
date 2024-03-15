package com.oasis.taskmanagement.controller;

import com.oasis.taskmanagement.dtos.requestDtos.TaskCategoryRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.TaskCategoryResponseDto;
import com.oasis.taskmanagement.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('category_write')")
    public ResponseEntity<ApiResponse<TaskCategoryResponseDto>> createCategory(@RequestBody @Valid TaskCategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequestDto));
    }

    @GetMapping("/list-all")
    @PreAuthorize("hasAnyAuthority('category_read')")
    public ResponseEntity<ApiResponse<List<TaskCategoryResponseDto>>> listAll() {
        return ResponseEntity.ok(categoryService.listAll());
    }
}
