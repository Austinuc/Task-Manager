package com.oasis.taskmanagement.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}
