package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.TaskRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.TaskResponseDto;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    @Test
    void testFromDtoToEntity() {
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setCategory("Dance");
        requestDto.setName("name");
        requestDto.setDescription("description");
        TaskEntity entity = TaskMapper.INSTANCE.fromDtoToEntity(requestDto);
        assertNotNull(entity);
        assertNotNull(entity.getName());
    }

    @Test
    void testFromEntityToDto() {
        TaskCategoryEntity categoryEntity = new TaskCategoryEntity();
        categoryEntity.setName("Dance");
        TaskEntity entity = new TaskEntity();
        entity.setId(1L);
        entity.setCategory(categoryEntity);
        entity.setName("Name");
        entity.setStatus(TaskStatus.PENDING);

        TaskResponseDto responseDto = TaskMapper.INSTANCE.fromEntityToDto(entity);
        assertNotNull(responseDto);
        assertNotNull(responseDto.getCategory());
        assertNotNull(responseDto.getName());

    }
}