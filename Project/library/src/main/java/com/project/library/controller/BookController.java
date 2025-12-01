package com.project.library.controller;

import com.project.library.dto.BookDto;
import com.project.library.dto.BookResponseDto;
import com.project.library.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto create(@RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable Long id, @RequestBody BookDto dto) {
        return bookService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
