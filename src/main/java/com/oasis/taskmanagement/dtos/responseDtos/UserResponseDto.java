package com.oasis.taskmanagement.dtos.responseDtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private List<TaskResponseDto> tasks;

}
