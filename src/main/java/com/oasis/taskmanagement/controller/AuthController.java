package com.oasis.taskmanagement.controller;

import com.oasis.taskmanagement.dtos.requestDtos.UserLoginDto;
import com.oasis.taskmanagement.dtos.requestDtos.UserSignupDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;
import com.oasis.taskmanagement.services.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Operation(summary = "Creates a new user account",
            description = "Email must be unique")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody @Valid UserSignupDto userSignupDto) {
        return ResponseEntity.ok().body(userAuthService.registerUser(userSignupDto));
    }

    @Operation(summary = "Login User",
            description = "Returns Access Token")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody @Valid UserLoginDto userLoginDto) {
        return ResponseEntity.ok().body(userAuthService.loginUser(userLoginDto));
    }

}
