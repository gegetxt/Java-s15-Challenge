package tests.member;

import com.library.book.Author;
import com.library.book.Book;
import com.library.enums.MemberType;
import com.library.member.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    private Reader reader;
    private Author author;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;

    @BeforeEach
    void setUp() {
        reader = new Reader(1, "Ali", "05550000000", "Ankara", MemberType.STUDENT, 5);
        author = new Author(1, "Orhan Pamuk");

        book1 = new Book(1, "Book One", author, "Novel", "ISBN-001", 100);
        book2 = new Book(2, "Book Two", author, "Novel", "ISBN-002", 110);
        book3 = new Book(3, "Book Three", author, "Novel", "ISBN-003", 120);
        book4 = new Book(4, "Book Four", author, "Novel", "ISBN-004", 130);
        book5 = new Book(5, "Book Five", author, "Novel", "ISBN-005", 140);
        book6 = new Book(6, "Book Six", author, "Novel", "ISBN-006", 150);
    }

    @Test
    void borrowBook_shouldAddBookSuccessfully() {
        boolean result = reader.borrowBook(book1);

        assertTrue(result);
        assertEquals(1, reader.getBorrowedBooks().size());
        assertTrue(reader.getBorrowedBooks().contains(book1));
    }

    @Test
    void borrowBook_shouldNotAddSameBookTwice() {
        reader.borrowBook(book1);
        boolean secondTry = reader.borrowBook(book1);

        assertFalse(secondTry);
        assertEquals(1, reader.getBorrowedBooks().size());
    }

    @Test
    void borrowBook_shouldNotExceedMaxBookLimit() {
        assertTrue(reader.borrowBook(book1));
        assertTrue(reader.borrowBook(book2));
        assertTrue(reader.borrowBook(book3));
        assertTrue(reader.borrowBook(book4));
        assertTrue(reader.borrowBook(book5));

        boolean sixthBookResult = reader.borrowBook(book6);

        assertFalse(sixthBookResult);
        assertEquals(5, reader.getBorrowedBooks().size());
        assertTrue(reader.hasReachedLimit());
    }

    @Test
    void returnBook_shouldRemoveBookSuccessfully() {
        reader.borrowBook(book1);

        boolean result = reader.returnBook(book1);

        assertTrue(result);
        assertEquals(0, reader.getBorrowedBooks().size());
        assertFalse(reader.getBorrowedBooks().contains(book1));
    }

    @Test
    void hasReachedLimit_shouldReturnFalseWhenLimitNotReached() {
        reader.borrowBook(book1);
        reader.borrowBook(book2);

        assertFalse(reader.hasReachedLimit());
    }

    @Test
    void hasReachedLimit_shouldReturnTrueWhenLimitReached() {
        reader.borrowBook(book1);
        reader.borrowBook(book2);
        reader.borrowBook(book3);
        reader.borrowBook(book4);
        reader.borrowBook(book5);

        assertTrue(reader.hasReachedLimit());
    }
}