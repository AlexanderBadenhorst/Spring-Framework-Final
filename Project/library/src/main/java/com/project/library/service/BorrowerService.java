package com.project.library.service;

import com.project.library.dto.BorrowerDto;
import com.project.library.exception.ResourceNotFoundException;
import com.project.library.model.Borrower;
import com.project.library.repository.BorrowerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    // ---------------------------
    // DTO MAPPERS
    // ---------------------------

    private BorrowerDto toDto(Borrower borrower) {
        BorrowerDto dto = new BorrowerDto();
        dto.setId(borrower.getId());
        dto.setFullName(borrower.getFullName());
        dto.setEmail(borrower.getEmail());
        dto.setPhoneNumber(borrower.getPhoneNumber());
        return dto;
    }

    private Borrower fromDto(BorrowerDto dto) {
        return Borrower.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    // ---------------------------
    // CRUD
    // ---------------------------

    public List<BorrowerDto> getAll() {
        return borrowerRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public BorrowerDto getById(Long id) {
        return borrowerRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));
    }

    public BorrowerDto create(BorrowerDto dto) {
        Borrower saved = borrowerRepository.save(fromDto(dto));
        return toDto(saved);
    }

    public BorrowerDto update(Long id, BorrowerDto dto) {
        Borrower existing = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));

        existing.setFullName(dto.getFullName());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());

        return toDto(borrowerRepository.save(existing));
    }

    public void delete(Long id) {
        borrowerRepository.delete(
                borrowerRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"))
        );
    }
}
