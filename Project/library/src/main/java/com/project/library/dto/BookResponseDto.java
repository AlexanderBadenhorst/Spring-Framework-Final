package com.project.library.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private String authorName;
    private Integer totalCopies;
    private Integer availableCopies;
}
