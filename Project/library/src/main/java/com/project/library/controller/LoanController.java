package com.project.library.controller;

import com.project.library.dto.LoanDto;
import com.project.library.service.LoanService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public List<LoanDto> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public LoanDto getById(@PathVariable Long id) {
        return loanService.getById(id);
    }

    @PostMapping("/borrow/{bookId}/{borrowerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDto borrow(@PathVariable Long bookId, @PathVariable Long borrowerId) {
        return loanService.borrowBook(bookId, borrowerId);
    }

    @PostMapping("/return/{loanId}")
    public LoanDto returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
}
