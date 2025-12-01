-- ============================
-- AUTHORS
-- ============================
INSERT INTO AUTHOR (first_name, last_name, biography) VALUES
                                                          ('J.R.R.', 'Tolkien', 'Author of The Lord of the Rings'),
                                                          ('George', 'Orwell', 'Author of 1984'),
                                                          ('Mary', 'Shelley', 'Author of Frankenstein');

-- ============================
-- BOOKS
-- ============================
-- Note: author_id must match the ORDER above
-- Tolkien = 1, Orwell = 2, Shelley = 3
INSERT INTO BOOK (title, isbn, publication_year, author_id, total_copies, available_copies) VALUES
                                                                                                ('The Hobbit', 'ISBN-001', 1937, 1, 5, 5),
                                                                                                ('1984', 'ISBN-002', 1949, 2, 5, 5),
                                                                                                ('Frankenstein', 'ISBN-003', 1818, 3, 5, 5);

-- ============================
-- BORROWERS
-- ============================
INSERT INTO BORROWER (full_name, email, phone_number) VALUES
                                                          ('Alice Johnson', 'alice@example.com', '000-0000'),
                                                          ('Bob Smith', 'bob@example.com', '000-0000');
