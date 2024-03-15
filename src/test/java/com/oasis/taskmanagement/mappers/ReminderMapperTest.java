package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.ReminderRequestDto;
import com.oasis.taskmanagement.dtos.responseDtos.ReminderResponseDto;
import com.oasis.taskmanagement.model.TaskEntity;
import com.oasis.taskmanagement.model.TaskReminder;
import com.oasis.taskmanagement.model.enums.ReminderStatus;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReminderMapperTest {

    @Test
    void testFromRequestToEntity() {
        ReminderRequestDto reminderRequestDto = new ReminderRequestDto();
        reminderRequestDto.setMessage("Wake up");
        reminderRequestDto.setStatus(ReminderStatus.ACTIVE);
        reminderRequestDto.setTaskId(1L);
        reminderRequestDto.setTime(OffsetDateTime.now(Clock.systemUTC()));

        TaskReminder entity = ReminderMapper.INSTANCE.fromRequestToEntity(reminderRequestDto);
        assertNotNull(entity);
        assertNotNull(entity.getTask());
        assertNotNull(entity.getMessage());
        assertEquals(entity.getStatus(), ReminderStatus.ACTIVE);
        assertNotNull(entity.getTime());
    }

    @Test
    void testFromEntityToResponse() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        TaskReminder reminderEntity = new TaskReminder();
        reminderEntity.setId(1L);
        reminderEntity.setTask(taskEntity);
        reminderEntity.setMessage("Start dancing");
        reminderEntity.setTime(OffsetDateTime.now());

        ReminderResponseDto reminderResponseDto = ReminderMapper.INSTANCE.fromEntityToResponse(reminderEntity);

        assertNotNull(reminderResponseDto);
        assertNotNull(reminderResponseDto.getId());
        assertNotNull(reminderResponseDto.getMessage());
        assertNotNull(reminderResponseDto.getTaskId());
        assertNotNull(reminderResponseDto.getTime());
    }
}