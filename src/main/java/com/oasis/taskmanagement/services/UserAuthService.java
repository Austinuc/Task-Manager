package com.oasis.taskmanagement.services;

import com.oasis.taskmanagement.dtos.requestDtos.UserLoginDto;
import com.oasis.taskmanagement.dtos.requestDtos.UserSignupDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;

public interface UserAuthService {
    ApiResponse<UserResponseDto> registerUser(UserSignupDto signupDto);
    ApiResponse<String> loginUser(UserLoginDto loginDto);
}
