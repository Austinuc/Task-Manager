package com.oasis.taskmanagement.model;

import com.oasis.taskmanagement.model.enums.Priority;
import com.oasis.taskmanagement.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class TaskEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @ManyToOne(targetEntity = UserEntity.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private OffsetDateTime dueDate;

    @ManyToOne(targetEntity = TaskCategoryEntity.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoty_id", nullable = false)
    private TaskCategoryEntity category;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<TaskReminder> reminder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(TaskCategoryEntity category) {
        this.category = category;
    }

    public List<TaskReminder> getReminder() {
        return reminder;
    }

    public void setReminder(List<TaskReminder> reminder) {
        this.reminder = reminder;
    }
}
