package com.oasis.taskmanagement.services.impl;

import com.oasis.taskmanagement.dtos.requestDtos.UserUpdateRequest;
import com.oasis.taskmanagement.dtos.responseDtos.ApiResponse;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;
import com.oasis.taskmanagement.exception.ResourceNotFoundException;
import com.oasis.taskmanagement.mappers.UserMapper;
import com.oasis.taskmanagement.model.UserEntity;
import com.oasis.taskmanagement.repositories.UserRepository;
import com.oasis.taskmanagement.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse<UserResponseDto> updateProfile(UserUpdateRequest userUpdateRequest) {
        Objects.requireNonNull(userUpdateRequest);
        Objects.requireNonNull(userUpdateRequest.getEmail(), "Email cannot be null");
        UserEntity userEntity = userRepository.findByEmail(userUpdateRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist for update"));
        if (shouldUpdate(userEntity, userUpdateRequest)) {
            userEntity = userRepository.save(userEntity);
            return new ApiResponse<>(
                    "Successful",
                    true,
                    UserMapper.INSTANCE.fromEntityToResponse(userEntity));
        }
        return new ApiResponse<>(
                "Failed",
                false,
                null);
    }

    private boolean shouldUpdate(UserEntity userEntity, UserUpdateRequest userUpdateRequest) {
        Objects.requireNonNull(userEntity);
        Objects.requireNonNull(userUpdateRequest);

        boolean shouldBeUpdated = false;

        if (userUpdateRequest.getEmail() != null && !userEntity.getEmail().equals(userUpdateRequest.getEmail())) {
            userEntity.setEmail(userUpdateRequest.getEmail());
            shouldBeUpdated = true;
        }
        if (userUpdateRequest.getFirstName() != null && !userEntity.getFirstName().equals(userUpdateRequest.getFirstName())) {
            userEntity.setFirstName(userUpdateRequest.getFirstName());
            shouldBeUpdated = true;
        }
        if (userUpdateRequest.getLastName() != null && !userEntity.getLastName().equals(userUpdateRequest.getLastName())) {
            userEntity.setLastName(userUpdateRequest.getLastName());
            shouldBeUpdated = true;
        }
        return shouldBeUpdated;
    }

    @Override
    public ApiResponse<UserResponseDto> viewProfile(Long userId) {
        assert userId != null : "userId cannot be null";
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity.map(entity -> new ApiResponse<>(
                "Successful",
                true,
                UserMapper.INSTANCE.fromEntityToResponse(entity))).orElse(null);
    }
}
