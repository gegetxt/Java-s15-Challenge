package com.library.library;

import com.library.book.Book;
import com.library.book.BookService;
import com.library.borrow.BorrowRecord;
import com.library.borrow.BorrowService;
import com.library.member.MemberService;
import com.library.member.Reader;

import java.util.List;

public class LibraryService {

    private Library library;
    private BookService bookService;
    private MemberService memberService;
    private BorrowService borrowService;

    public LibraryService(Library library, BookService bookService, MemberService memberService, BorrowService borrowService) {
        this.library = library;
        this.bookService = bookService;
        this.memberService = memberService;
        this.borrowService = borrowService;
    }

    public void registerBook(Book book) {
        if (book == null) {
            System.out.println("Book cannot be null.");
            return;
        }

        bookService.addBook(book);
        library.addBook(book);
    }

    public void registerReader(Reader reader) {
        if (reader == null) {
            System.out.println("Reader cannot be null.");
            return;
        }

        memberService.addReader(reader);
        library.addReader(reader);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public List<Reader> getAllReaders() {
        return memberService.getAllReaders();
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowService.getAllBorrowRecords();
    }

    public void printLibrarySummary() {
        System.out.println("Library Name: " + library.getName());
        System.out.println("Total Books: " + bookService.getAllBooks().size());
        System.out.println("Total Readers: " + memberService.getAllReaders().size());
        System.out.println("Total Borrow Records: " + borrowService.getAllBorrowRecords().size());
    }
}