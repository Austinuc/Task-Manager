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
}
