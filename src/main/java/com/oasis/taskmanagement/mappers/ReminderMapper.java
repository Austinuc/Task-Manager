package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ReminderResponseDto;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.TaskReminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReminderMapper {
    ReminderMapper INSTANCE = Mappers.getMapper(ReminderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "task", source = "taskId")
    TaskReminder fromRequestToEntity(ReminderRequestDto reminderRequestDto);

    @Mapping(target = "taskId", source = "task")
    ReminderResponseDto fromEntityToResponse(TaskReminder reminder);

    default TaskEntity mapTaskIdToEntity(Long taskId) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        return taskEntity;
    }

    default Long mapTaskEntityToId(TaskEntity taskEntity) {
        return taskEntity.getId();
    }
}
