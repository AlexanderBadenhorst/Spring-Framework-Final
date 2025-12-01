package com.project.library.service;

import com.project.library.dto.AuthorDto;
import com.project.library.model.Author;
import com.project.library.repository.AuthorRepository;
import com.project.library.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // ---------------------------
    // DTO MAPPERS
    // ---------------------------
    private AuthorDto toDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getFirstName() + " " + author.getLastName());
        return dto;
    }

    private Author fromDto(AuthorDto dto) {
        String[] parts = dto.getName().split(" ", 2);
        String first = parts[0];
        String last = parts.length > 1 ? parts[1] : "";

        return Author.builder()
                .firstName(first)
                .lastName(last)
                .build();
    }

    // ---------------------------
    // CRUD OPERATIONS
    // ---------------------------

    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public AuthorDto getById(Long id) {
        return authorRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }

    public AuthorDto create(AuthorDto dto) {
        Author saved = authorRepository.save(fromDto(dto));
        return toDto(saved);
    }

    public AuthorDto update(Long id, AuthorDto dto) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        String[] parts = dto.getName().split(" ", 2);
        existing.setFirstName(parts[0]);
        existing.setLastName(parts.length > 1 ? parts[1] : "");

        return toDto(authorRepository.save(existing));
    }

    public void delete(Long id) {
        authorRepository.delete(
                authorRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found"))
        );
    }
}
