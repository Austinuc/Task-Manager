package com.oasis.taskmanagement.mappers;

import com.oasis.taskmanagement.dtos.requestDtos.UserSignupDto;
import com.oasis.taskmanagement.dtos.responseDtos.UserResponseDto;
import com.oasis.taskmanagement.model.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void testFromDtoToEntity() {
        UserSignupDto signupDto = new UserSignupDto();
        signupDto.setEmail("test@test.com");
        signupDto.setFirstName("John");
        signupDto.setLastName("Deo");
        signupDto.setPassword("password");

        UserEntity entity = UserMapper.INSTANCE.fromDtoToEntity(signupDto);
        assertNotNull(entity);
        assertNotNull(entity.getFirstName());
        assertNotNull(entity.getPassword());
        assertNotNull(entity.getEmail());
    }

    @Test
    void testFromEntityToResponse() {

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setEmail("test@test.com");
        entity.setFirstName("John");
        entity.setPassword("password");

        UserResponseDto responseDto = UserMapper.INSTANCE.fromEntityToResponse(entity);

        assertNotNull(responseDto);
        assertNotNull(responseDto.getFirstName());
        assertNotNull(responseDto.getEmail());
    }
}