package tests.book;

import com.library.book.Author;
import com.library.book.Book;
import com.library.book.BookRepository;
import com.library.book.BookService;
import com.library.enums.BookStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;
    private Author author1;
    private Author author2;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        bookService = new BookService(bookRepository);

        author1 = new Author(1, "Ahmet Hamdi Tanpınar");
        author2 = new Author(2, "Sabahattin Ali");

        book1 = new Book(1, "Huzur", author1, "Novel", "ISBN-001", 100);
        book2 = new Book(2, "Kürk Mantolu Madonna", author2, "Novel", "ISBN-002", 120);
        book3 = new Book(3, "Saatleri Ayarlama Enstitüsü", author1, "Novel", "ISBN-003", 130);
    }

    @Test
    void addBook_shouldSaveBookSuccessfully() {
        bookService.addBook(book1);

        Book foundBook = bookService.getBookById(1);

        assertNotNull(foundBook);
        assertEquals("Huzur", foundBook.getTitle());
        assertEquals(BookStatus.AVAILABLE, foundBook.getStatus());
    }

    @Test
    void addBook_shouldAlsoAddBookToAuthorList() {
        bookService.addBook(book1);

        assertTrue(author1.getBooks().contains(book1));
        assertEquals(1, author1.getBooks().size());
    }

    @Test
    void getAllBooks_shouldReturnAllSavedBooks() {
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);

        List<Book> books = bookService.getAllBooks();

        assertEquals(3, books.size());
    }

    @Test
    void getBooksByAuthorName_shouldReturnMatchingBooks() {
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);

        List<Book> booksByAuthor = bookService.getBooksByAuthorName("Ahmet Hamdi Tanpınar");

        assertEquals(2, booksByAuthor.size());
        assertTrue(booksByAuthor.contains(book1));
        assertTrue(booksByAuthor.contains(book3));
    }

    @Test
    void getBooksByCategory_shouldReturnMatchingBooks() {
        bookService.addBook(book1);
        bookService.addBook(book2);

        List<Book> booksByCategory = bookService.getBooksByCategory("Novel");

        assertEquals(2, booksByCategory.size());
    }

    @Test
    void updateBook_shouldChangeBookInformation() {
        bookService.addBook(book1);

        bookService.updateBook(1, "Yeni Huzur", author2, "Classic", "ISBN-NEW", 200);

        Book updatedBook = bookService.getBookById(1);

        assertEquals("Yeni Huzur", updatedBook.getTitle());
        assertEquals(author2, updatedBook.getAuthor());
        assertEquals("Classic", updatedBook.getCategory());
        assertEquals("ISBN-NEW", updatedBook.getIsbn());
        assertEquals(200, updatedBook.getPrice());
    }

    @Test
    void deleteBook_shouldRemoveBookWhenAvailable() {
        bookService.addBook(book1);

        bookService.deleteBook(1);

        assertNull(bookRepository.findById(1));
    }

    @Test
    void deleteBook_shouldNotRemoveBorrowedBook() {
        bookService.addBook(book1);
        book1.markAsBorrowed();

        bookService.deleteBook(1);

        assertNotNull(bookRepository.findById(1));
    }
}