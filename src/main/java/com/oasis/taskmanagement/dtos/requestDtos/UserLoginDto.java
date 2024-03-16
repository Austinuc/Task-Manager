package com.oasis.taskmanagement.dtos.requestDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotNull
    @Schema(example = "austin5astro@gmail.com")
    @Email
    private String email;

    @NotNull @Schema(example = "1234")
    private String password;

}
