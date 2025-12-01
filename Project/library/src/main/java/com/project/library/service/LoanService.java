package com.project.library.service;

import com.project.library.dto.LoanDto;
import com.project.library.exception.ResourceNotFoundException;
import com.project.library.model.Book;
import com.project.library.model.Borrower;
import com.project.library.model.Loan;
import com.project.library.repository.BookRepository;
import com.project.library.repository.BorrowerRepository;
import com.project.library.repository.LoanRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    // ---------------------------
    // DTO MAPPERS
    // ---------------------------

    private LoanDto toDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBookId(loan.getBook().getId());
        dto.setBorrowerId(loan.getBorrower().getId());
        return dto;
    }

    // ---------------------------
    // CRUD + BUSINESS LOGIC
    // ---------------------------

    public List<LoanDto> getAll() {
        return loanRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public LoanDto getById(Long id) {
        return loanRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
    }

    public LoanDto borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies for this book.");
        }

        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));

        // Update book stock
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loan loan = Loan.builder()
                .book(book)
                .borrower(borrower)
                .loanDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .build();

        return toDto(loanRepository.save(loan));
    }

    public LoanDto returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (loan.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned.");
        }

        loan.setReturnDate(LocalDate.now());

        // Increase stock
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return toDto(loanRepository.save(loan));
    }

    public void delete(Long id) {
        loanRepository.delete(
                loanRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Loan not found"))
        );
    }
}
