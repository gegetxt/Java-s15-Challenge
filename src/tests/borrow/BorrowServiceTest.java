package tests.borrow;

import com.library.book.Author;
import com.library.book.Book;
import com.library.book.BookRepository;
import com.library.book.BookService;
import com.library.borrow.BorrowRecord;
import com.library.borrow.BorrowRepository;
import com.library.borrow.BorrowService;
import com.library.enums.BookStatus;
import com.library.invoice.Invoice;
import com.library.invoice.InvoiceService;
import com.library.library.Library;
import com.library.member.MemberRepository;
import com.library.member.MemberService;
import com.library.member.Reader;
import com.library.member.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {

    private BorrowRepository borrowRepository;
    private InvoiceService invoiceService;
    private MemberRepository memberRepository;
    private MemberService memberService;
    private Library library;
    private BorrowService borrowService;
    private BookService bookService;
    private Reader reader;
    private Author author;
    private Book book;

    @BeforeEach
    void setUp() {
        borrowRepository = new BorrowRepository();
        invoiceService = new InvoiceService();
        memberRepository = new MemberRepository();
        memberService = new MemberService(memberRepository);
        library = new Library("Central Library");

        BookRepository bookRepository = new BookRepository();
        bookService = new BookService(bookRepository);

        borrowService = new BorrowService(
                borrowRepository,
                invoiceService,
                memberService,
                library
        );

        reader = new Student(1, "Ayse", "05550000000", "Ankara");
        memberService.addReader(reader);

        author = new Author(1, "Yasar Kemal");
        book = new Book(1, "Ince Memed", author, "Novel", "ISBN-100", 150);
        bookService.addBook(book);
    }

    @Test
    void borrowBook_shouldCreateBorrowRecordAndMarkBookAsBorrowed() {
        borrowService.borrowBook(1, reader, book, 50);

        BorrowRecord record = borrowService.getActiveBorrowRecordByBook(book);

        assertNotNull(record);
        assertEquals(BookStatus.BORROWED, book.getStatus());
        assertTrue(reader.getBorrowedBooks().contains(book));
        assertEquals(1, memberService.getMemberRecordByReaderId(reader.getId()).getCurrentBorrowedBookCount());
    }

    @Test
    void borrowBook_shouldCreateInvoice() {
        borrowService.borrowBook(1, reader, book, 50);

        Invoice invoice = invoiceService.findActiveInvoice(reader, book);

        assertNotNull(invoice);
        assertEquals(50, invoice.getAmount());
        assertFalse(invoice.isRefunded());
    }

    @Test
    void borrowBook_shouldNotBorrowAlreadyBorrowedBook() {
        borrowService.borrowBook(1, reader, book, 50);

        Reader secondReader = new Student(2, "Mehmet", "05559999999", "Istanbul");
        memberService.addReader(secondReader);

        borrowService.borrowBook(2, secondReader, book, 50);

        List<BorrowRecord> allRecords = borrowService.getAllBorrowRecords();

        assertEquals(1, allRecords.size());
    }

    @Test
    void borrowBook_shouldNotAllowReaderToExceedLimit() {
        Author commonAuthor = new Author(2, "Test Author");

        for (int i = 1; i <= 5; i++) {
            Book tempBook = new Book(100 + i, "Book " + i, commonAuthor, "Novel", "ISBN-" + i, 100 + i);
            bookService.addBook(tempBook);
            borrowService.borrowBook(i, reader, tempBook, 20);
        }

        Book sixthBook = new Book(200, "Sixth Book", commonAuthor, "Novel", "ISBN-200", 200);
        bookService.addBook(sixthBook);

        borrowService.borrowBook(99, reader, sixthBook, 20);

        assertFalse(reader.getBorrowedBooks().contains(sixthBook));
        assertEquals(5, reader.getBorrowedBooks().size());
    }

    @Test
    void returnBook_shouldMarkBookAsAvailableAndRefundInvoice() {
        borrowService.borrowBook(1, reader, book, 50);

        borrowService.returnBook(book, reader);

        assertEquals(BookStatus.AVAILABLE, book.getStatus());
        assertFalse(reader.getBorrowedBooks().contains(book));
        assertEquals(0, memberService.getMemberRecordByReaderId(reader.getId()).getCurrentBorrowedBookCount());

        Invoice invoice = invoiceService.findById(1);
        assertNotNull(invoice);
        assertTrue(invoice.isRefunded());
    }

    @Test
    void isBookCurrentlyBorrowed_shouldReturnTrueAfterBorrow() {
        borrowService.borrowBook(1, reader, book, 50);

        assertTrue(borrowService.isBookCurrentlyBorrowed(book));
    }

    @Test
    void isBookCurrentlyBorrowed_shouldReturnFalseAfterReturn() {
        borrowService.borrowBook(1, reader, book, 50);
        borrowService.returnBook(book, reader);

        assertFalse(borrowService.isBookCurrentlyBorrowed(book));
    }
}
