package com.oasis.taskmanagement.services;

import com.oasis.taskmanagement.dtos.requestDtos.UserUpdateRequest;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;

public interface UserService {
    ApiResponse<UserResponseDto> updateProfile(UserUpdateRequest userUpdateRequest);
    ApiResponse<UserResponseDto> viewProfile(Long userId);

}
