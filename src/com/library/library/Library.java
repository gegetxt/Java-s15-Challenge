package com.library.library;

import com.library.book.Book;
import com.library.borrow.BorrowRecord;
import com.library.member.Reader;

import java.util.HashMap;
import java.util.Map;

public class Library {

    private String name;
    private Map<Integer, Book> books;
    private Map<Integer, Reader> readers;
    private Map<Integer, BorrowRecord> borrowRecords;

    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.readers = new HashMap<>();
        this.borrowRecords = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Integer, Reader> getReaders() {
        return readers;
    }

    public Map<Integer, BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void addBook(Book book) {
        if (book != null) {
            books.put(book.getId(), book);
        }
    }

    public void addReader(Reader reader) {
        if (reader != null) {
            readers.put(reader.getId(), reader);
        }
    }

    public void addBorrowRecord(BorrowRecord borrowRecord) {
        if (borrowRecord != null) {
            borrowRecords.put(borrowRecord.getId(), borrowRecord);
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", totalBooks=" + books.size() +
                ", totalReaders=" + readers.size() +
                ", totalBorrowRecords=" + borrowRecords.size() +
                '}';
    }
}
