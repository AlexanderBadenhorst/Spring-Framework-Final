package com.project.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
}
