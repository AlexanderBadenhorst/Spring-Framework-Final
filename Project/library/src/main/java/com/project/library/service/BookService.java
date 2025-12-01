package com.project.library.service;

import com.project.library.dto.BookDto;
import com.project.library.dto.BookResponseDto;
import com.project.library.exception.ResourceNotFoundException;
import com.project.library.model.Author;
import com.project.library.model.Book;
import com.project.library.repository.AuthorRepository;
import com.project.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    // ---------------------------
    // CREATE
    // ---------------------------

    public BookResponseDto create(BookDto dto) {

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Book book = Book.builder()
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .publicationYear(dto.getPublicationYear())
                .author(author)
                .totalCopies(dto.getTotalCopies())
                .availableCopies(dto.getTotalCopies()) // start equal
                .build();

        return toResponse(bookRepository.save(book));
    }

    // ---------------------------
    // READ
    // ---------------------------

    public List<BookResponseDto> getAll() {
        return bookRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public BookResponseDto getById(Long id) {
        return bookRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    // ---------------------------
    // UPDATE
    // ---------------------------

    public BookResponseDto update(Long id, BookDto dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setAuthor(author);

        // Adjust copies
        book.setTotalCopies(dto.getTotalCopies());
        if (book.getAvailableCopies() > dto.getTotalCopies()) {
            book.setAvailableCopies(dto.getTotalCopies());
        }

        return toResponse(bookRepository.save(book));
    }

    // ---------------------------
    // DELETE
    // ---------------------------

    public void delete(Long id) {
        bookRepository.delete(
                bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Book not found"))
        );
    }

    // ---------------------------
    // DTO MAPPER
    // ---------------------------

    private BookResponseDto toResponse(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .authorName(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .build();
    }
}
