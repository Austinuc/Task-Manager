package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.TaskCategoryRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.TaskCategoryResponseDto;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    void testFromRequestToEntity() {
        TaskCategoryRequestDto categoryRequestDto = new TaskCategoryRequestDto();
        categoryRequestDto.setName("Games");
        categoryRequestDto.setDescription("Gaming everyday");

        TaskCategoryEntity categoryEntity = CategoryMapper.INSTANCE.fromRequestToEntity(categoryRequestDto);

        assertNotNull(categoryEntity);
        assertNotNull(categoryEntity.getName());
        assertNotNull(categoryEntity.getDescription());
    }

    @Test
    void testFromEntityToResponseDto() {
        TaskCategoryEntity categoryEntity = new TaskCategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Category");

        TaskCategoryResponseDto categoryResponseDto = CategoryMapper.INSTANCE.fromEntityToResponseDto(categoryEntity);
        assertNotNull(categoryResponseDto);
        assertNotNull(categoryResponseDto.getId());
        assertNotNull(categoryResponseDto.getName());
    }
}