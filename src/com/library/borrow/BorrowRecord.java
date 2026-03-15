package com.library.borrow;

import com.library.book.Book;
import com.library.member.Reader;

import java.time.LocalDate;

public class BorrowRecord {

    private int id;
    private Book book;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    public BorrowRecord(int id, Book book, Reader reader, LocalDate borrowDate) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.returnDate = null;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book != null) {
            this.book = book;
        }
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        if (reader != null) {
            this.reader = reader;
        }
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        if (borrowDate != null) {
            this.borrowDate = borrowDate;
        }
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markAsReturned(LocalDate returnDate) {
        if (returnDate != null) {
            this.returnDate = returnDate;
            this.returned = true;
        }
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id=" + id +
                ", book=" + book.getTitle() +
                ", reader=" + reader.getName() +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", returned=" + returned +
                '}';
    }
}
