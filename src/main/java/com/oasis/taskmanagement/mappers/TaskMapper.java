package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.TaskRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.TaskResponseDto;
import com.oasis.taskmanagement.model.TaskCategoryEntity;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    TaskEntity fromDtoToEntity(TaskRequestDto taskRequestDto);

    @Mapping(target = "userId", source = "user")
    TaskResponseDto fromEntityToDto(TaskEntity taskEntity);

    default String mapCategoryEntityToId(TaskCategoryEntity categoryEntity) {
        return categoryEntity.getName();
    }

    default Long mapToId(UserEntity userEntity) {
        if (userEntity != null) {
            return userEntity.getId();
        }
        return null;
    }
}
