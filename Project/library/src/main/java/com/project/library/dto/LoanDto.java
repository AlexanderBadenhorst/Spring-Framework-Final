package com.project.library.dto;

import lombok.Data;

@Data
public class LoanDto {
    private Long id;
    private Long bookId;
    private Long borrowerId;
}
