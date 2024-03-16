package com.oasis.taskmanagement.dtos.requestDtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskCategoryRequestDto {
    @NotNull
    private String name;
    private String description;
}
