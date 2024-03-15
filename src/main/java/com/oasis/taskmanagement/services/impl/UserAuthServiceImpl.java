package com.oasis.taskmanagement.services.impl;

import com.oasis.taskmanagement.dtos.requestDtos.UserLoginDto;
import com.oasis.taskmanagement.dtos.requestDtos.UserSignupDto;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;
import com.oasis.taskmanagement.enums.Roles;
import com.oasis.taskmanagement.exception.AuthenticationException;
import com.oasis.taskmanagement.exception.DataValidationException;
import com.oasis.taskmanagement.exception.ResourceNotFoundException;
import com.oasis.taskmanagement.mappers.UserMapper;
import com.oasis.taskmanagement.model.UserEntity;
import com.oasis.taskmanagement.repositories.UserRepository;
import com.oasis.taskmanagement.security.JwtTokenProvider;
import com.oasis.taskmanagement.services.UserAuthService;
import com.oasis.taskmanagement.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    private final static Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthenticationManager auth;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserAuthServiceImpl(UserRepository userRepository, AuthenticationManager auth,
                               JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.auth = auth;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResponse<UserResponseDto> registerUser(UserSignupDto signupDto) {
        validateSignupDetails(signupDto);
        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        UserEntity userEntity = UserMapper.INSTANCE.fromDtoToEntity(signupDto);
        userEntity.setRoles(Roles.USER.name());

        return new ApiResponse<UserResponseDto>(
                "User registered successfully",
                true,
                UserMapper.INSTANCE.fromEntityToResponse(userRepository.save(userEntity)));
    }

    private void validateSignupDetails(UserSignupDto signupDto) {
        Objects.requireNonNull(signupDto);
        if (signupDto.getEmail() == null || signupDto.getPassword() == null || signupDto.getFirstName() == null) {
            logger.warn("missing required field");
            throw new DataValidationException("Missing required fields");
        }
        if (!AppUtil.validEmail(signupDto.getEmail())) {
            logger.warn("Email not valid");
            throw new DataValidationException("Email not valid");
        }
    }

    @Override
    public ApiResponse<String> loginUser(UserLoginDto loginDto) {
        userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        try {
            Authentication authentication = auth.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return new ApiResponse<>(
                        "Login successful",
                        true,
                        jwtTokenProvider.generateToken(authentication)
                );

            } else {
                throw new AuthenticationException("Invalid username or password");
            }

        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
