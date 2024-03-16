package com.oasis.taskmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "category")
public class TaskCategoryEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
}
