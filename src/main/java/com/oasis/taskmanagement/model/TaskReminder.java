package com.oasis.taskmanagement.model;

import com.oasis.taskmanagement.model.enums.ReminderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class TaskReminder extends BaseEntity{

    @ManyToOne(targetEntity = TaskEntity.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReminderStatus status;

    @Column(nullable = false)
    private OffsetDateTime time;
}
