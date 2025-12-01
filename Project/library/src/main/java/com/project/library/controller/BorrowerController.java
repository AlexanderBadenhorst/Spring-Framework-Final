package com.project.library.controller;

import com.project.library.dto.BorrowerDto;
import com.project.library.service.BorrowerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor
public class BorrowerController {

    private final BorrowerService borrowerService;

    @GetMapping
    public List<BorrowerDto> getAll() {
        return borrowerService.getAll();
    }

    @GetMapping("/{id}")
    public BorrowerDto getById(@PathVariable Long id) {
        return borrowerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowerDto create(@Valid @RequestBody BorrowerDto dto) {
        return borrowerService.create(dto);
    }

    @PutMapping("/{id}")
    public BorrowerDto update(@PathVariable Long id, @Valid @RequestBody BorrowerDto dto) {
        return borrowerService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        borrowerService.delete(id);
    }
}
