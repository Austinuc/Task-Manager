package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.UserSignupDto;
import com.oasis.taskmanagement.dtos.responseDtos.TaskResponseDto;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    UserEntity fromDtoToEntity(UserSignupDto signupDto);
    UserResponseDto fromEntityToResponse(UserEntity userEntity);

    default List<TaskResponseDto> mapTasksFromEntity(List<TaskEntity> tasks) {
        if (tasks != null && !tasks.isEmpty()) {
            return tasks.stream()
                    .map(TaskMapper.INSTANCE::fromEntityToDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
