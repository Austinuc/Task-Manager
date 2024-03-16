package com.oasis.taskmanagement.dtos.requestDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String firstName;

    private String lastName;

    @Schema(example = "austin5astro@gmail.com")
    @Email
    private String email;

}
