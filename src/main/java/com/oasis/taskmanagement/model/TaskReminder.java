package com.oasis.taskmanagement.model;

import com.oasis.taskmanagement.model.enums.ReminderStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

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

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public String getMessage() {
        return message;
    }

    public ReminderStatus getStatus() {
        return status;
    }

    public void setStatus(ReminderStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }
}
