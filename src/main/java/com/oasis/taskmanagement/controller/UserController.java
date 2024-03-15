package com.oasis.taskmanagement.controller;

import com.oasis.taskmanagement.dtos.requestDtos.UserUpdateRequest;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;
import com.oasis.taskmanagement.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyAuthority('profile_write')")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateProfile(
            @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.updateProfile(userUpdateRequest));
    }

    @PreAuthorize("hasAnyAuthority('profile_read')")
    @GetMapping("/view/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> viewProfile(
            @PathVariable @Valid Long userId) {
        return ResponseEntity.ok(userService.viewProfile(userId));
    }
}
