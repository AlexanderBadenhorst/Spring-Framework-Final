package com.project.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    private int publicationYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    @Min(0)
    private int totalCopies;

    @Min(0)
    private int availableCopies;
}
