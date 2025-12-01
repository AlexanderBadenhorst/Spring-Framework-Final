package com.project.library.controller;

import com.project.library.dto.AuthorDto;
import com.project.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto create(@RequestBody AuthorDto dto) {
        return authorService.create(dto);
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto dto) {
        return authorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
