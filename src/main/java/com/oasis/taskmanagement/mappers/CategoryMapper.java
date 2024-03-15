package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.TaskCategoryRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.TaskCategoryResponseDto;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    @Mapping(target = "id", ignore = true)
    TaskCategoryEntity fromRequestToEntity(TaskCategoryRequestDto categoryRequestDto);
    TaskCategoryResponseDto fromEntityToResponseDto(TaskCategoryEntity categoryEntity);

}
