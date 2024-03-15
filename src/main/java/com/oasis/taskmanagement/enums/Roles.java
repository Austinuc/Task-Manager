package com.oasis.taskmanagement.enums;

import java.util.List;

import static com.oasis.taskmanagement.enums.UserPermissions.*;


public enum Roles {
    ADMIN(List.of(TASK_READ, TASK_WRITE, TASK_DELETE, USERS_READ, USERS_DELETE, CATEGORY_READ, CATEGORY_WRITE)),
    USER(List.of(TASK_READ, TASK_WRITE, TASK_DELETE, PROFILE_WRITE, PROFILE_READ, CATEGORY_READ, CATEGORY_WRITE));

    private final List<UserPermissions> permissions;

    Roles(List<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public List<UserPermissions> getPermissions() {
        return permissions;
    }
}
