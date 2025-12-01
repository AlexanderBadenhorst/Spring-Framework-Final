package com.project.library.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotNull(message = "Publication year is required")
    private Integer publicationYear;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @Min(value = 1, message = "Total copies must be at least 1")
    private Integer totalCopies;
}
