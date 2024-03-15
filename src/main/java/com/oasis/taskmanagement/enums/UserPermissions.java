package com.oasis.taskmanagement.enums;


public enum UserPermissions {
    TASK_READ("task_read"),
    TASK_WRITE("task_write"),
    TASK_DELETE("task_delete"),
    PROFILE_WRITE("profile_write"),
    PROFILE_READ("profile_read"),
    USERS_READ("user_read"),
    USERS_DELETE("user_delete"),
    CATEGORY_READ("category_read"),
    CATEGORY_WRITE("category_write");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    UserPermissions(String permission) {
        this.permission = permission;
    }

}
