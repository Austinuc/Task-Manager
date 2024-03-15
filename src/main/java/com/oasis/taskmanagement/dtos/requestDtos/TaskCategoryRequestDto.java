package com.oasis.taskmanagement.dtos.requestDtos;

import jakarta.validation.constraints.NotNull;

public class TaskCategoryRequestDto {
    @NotNull
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
