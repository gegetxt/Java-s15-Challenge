package com.library.main;

import com.library.book.Author;
import com.library.book.Book;
import com.library.book.BookRepository;
import com.library.book.BookService;
import com.library.borrow.BorrowRepository;
import com.library.borrow.BorrowService;
import com.library.invoice.InvoiceService;
import com.library.library.Library;
import com.library.library.LibraryService;
import com.library.member.Faculty;
import com.library.member.Librarian;
import com.library.member.MemberRepository;
import com.library.member.MemberService;
import com.library.member.Reader;
import com.library.member.Student;
import com.library.util.InputUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Library library = new Library("Central Library");

        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);

        MemberRepository memberRepository = new MemberRepository();
        MemberService memberService = new MemberService(memberRepository);

        BorrowRepository borrowRepository = new BorrowRepository();
        InvoiceService invoiceService = new InvoiceService();

        BorrowService borrowService = new BorrowService(
                borrowRepository,
                invoiceService,
                memberService,
                library
        );

        LibraryService libraryService = new LibraryService(
                library,
                bookService,
                memberService,
                borrowService
        );

        Librarian librarian = new Librarian(
                1,
                "Library Admin",
                "05550000000",
                "Ankara",
                "EMP001",
                "1234"
        );

        boolean running = true;

        while (running) {
            System.out.println("\n===== WELCOME TO LIBRARY AUTOMATION SYSTEM =====");
            System.out.println("1- Librarian Panel");
            System.out.println("2- Reader Panel");
            System.out.println("0- Exit");

            int choice = InputUtil.readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    openLibrarianPanel(librarian, libraryService, bookService, memberService);
                    break;
                case 2:
                    openReaderPanel(bookService, memberService, borrowService);
                    break;
                case 0:
                    running = false;
                    System.out.println("System closed.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        InputUtil.closeScanner();
    }

    private static void openLibrarianPanel(Librarian librarian,
                                           LibraryService libraryService,
                                           BookService bookService,
                                           MemberService memberService) {

        System.out.println("\n===== LIBRARIAN LOGIN =====");
        String password = InputUtil.readString("Enter librarian password: ");

        if (!librarian.login(password)) {
            System.out.println("Wrong password.");
            return;
        }

        boolean librarianRunning = true;

        while (librarianRunning) {
            System.out.println("\n===== LIBRARIAN PANEL =====");
            System.out.println("1- Add Book");
            System.out.println("2- Update Book");
            System.out.println("3- Delete Book");
            System.out.println("4- List All Books");
            System.out.println("5- Search Book by ID");
            System.out.println("6- Search Books by Title");
            System.out.println("7- List Books by Category");
            System.out.println("8- List Books by Author");
            System.out.println("9- Add Reader");
            System.out.println("10- List All Readers");
            System.out.println("11- Show Library Summary");
            System.out.println("0- Logout");

            int choice = InputUtil.readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addBookFlow(libraryService);
                    break;
                case 2:
                    updateBookFlow(bookService);
                    break;
                case 3:
                    deleteBookFlow(bookService);
                    break;
                case 4:
                    listAllBooksFlow(libraryService);
                    break;
                case 5:
                    searchBookByIdFlow(bookService);
                    break;
                case 6:
                    searchBooksByTitleFlow(bookService);
                    break;
                case 7:
                    listBooksByCategoryFlow(bookService);
                    break;
                case 8:
                    listBooksByAuthorFlow(bookService);
                    break;
                case 9:
                    addReaderFlow(libraryService);
                    break;
                case 10:
                    listAllReadersFlow(libraryService);
                    break;
                case 11:
                    libraryService.printLibrarySummary();
                    break;
                case 0:
                    librarianRunning = false;
                    System.out.println("Logged out from librarian panel.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void openReaderPanel(BookService bookService,
                                        MemberService memberService,
                                        BorrowService borrowService) {

        System.out.println("\n===== READER LOGIN =====");
        int readerId = InputUtil.readInt("Enter Reader ID: ");

        Reader reader = memberService.getReaderById(readerId);

        if (reader == null) {
            System.out.println("Reader not found.");
            return;
        }

        boolean readerRunning = true;

        while (readerRunning) {
            System.out.println("\n===== READER PANEL =====");
            System.out.println("Welcome, " + reader.getName());
            System.out.println("1- List All Books");
            System.out.println("2- Borrow Book");
            System.out.println("3- Return Book");
            System.out.println("4- Show My Borrowed Books");
            System.out.println("5- Show My Member Info");
            System.out.println("0- Logout");

            int choice = InputUtil.readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    listAllBooksFlowDirect(bookService);
                    break;
                case 2:
                    borrowBookFlow(reader, bookService, borrowService);
                    break;
                case 3:
                    returnBookFlow(reader, bookService, borrowService);
                    break;
                case 4:
                    showMyBorrowedBooksFlow(reader);
                    break;
                case 5:
                    showMyMemberInfoFlow(reader, memberService);
                    break;
                case 0:
                    readerRunning = false;
                    System.out.println("Logged out from reader panel.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addBookFlow(LibraryService libraryService) {
        int bookId = InputUtil.readInt("Book id: ");
        String title = InputUtil.readString("Book title: ");

        int authorId = InputUtil.readInt("Author id: ");
        String authorName = InputUtil.readString("Author name: ");


        String category = InputUtil.readString("Category: ");
        String isbn = InputUtil.readString("ISBN: ");
        double price = InputUtil.readDouble("Price: ");

        Author author = new Author(authorId, authorName );
        Book book = new Book(bookId, title, author, category, isbn, price);

        libraryService.registerBook(book);
    }

    private static void updateBookFlow(BookService bookService) {
        int id = InputUtil.readInt("Enter book id to update: ");
        String newTitle = InputUtil.readString("New title: ");

        int authorId = InputUtil.readInt("New author id: ");
        String authorName = InputUtil.readString("New author name: ");



        String newCategory = InputUtil.readString("New category: ");
        String newIsbn = InputUtil.readString("New ISBN: ");
        double newPrice = InputUtil.readDouble("New price: ");

        Author newAuthor = new Author(authorId, authorName );
        bookService.updateBook(id, newTitle, newAuthor, newCategory, newIsbn, newPrice);
    }

    private static void deleteBookFlow(BookService bookService) {
        int id = InputUtil.readInt("Enter book id to delete: ");
        bookService.deleteBook(id);
    }

    private static void listAllBooksFlow(LibraryService libraryService) {
        List<Book> books = libraryService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void listAllBooksFlowDirect(BookService bookService) {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void searchBookByIdFlow(BookService bookService) {
        int id = InputUtil.readInt("Enter book id: ");
        try {
            Book book = bookService.getBookById(id);
            System.out.println(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchBooksByTitleFlow(BookService bookService) {
        String title = InputUtil.readString("Enter title: ");
        List<Book> books = bookService.getBooksByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void listBooksByCategoryFlow(BookService bookService) {
        String category = InputUtil.readString("Enter category: ");
        List<Book> books = bookService.getBooksByCategory(category);

        if (books.isEmpty()) {
            System.out.println("No books found in this category.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void listBooksByAuthorFlow(BookService bookService) {
        String authorName = InputUtil.readString("Enter author name: ");
        List<Book> books = bookService.getBooksByAuthorName(authorName);

        if (books.isEmpty()) {
            System.out.println("No books found for this author.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void addReaderFlow(LibraryService libraryService) {
        int readerId = InputUtil.readInt("Reader id: ");
        String readerName = InputUtil.readString("Reader name: ");
        String readerPhone = InputUtil.readString("Reader phone: ");
        String readerAddress = InputUtil.readString("Reader address: ");
        int readerTypeChoice = InputUtil.readInt("Reader type (1-Student, 2-Faculty): ");

        Reader reader;
        if (readerTypeChoice == 1) {
            reader = new Student(readerId, readerName, readerPhone, readerAddress);
        } else {
            reader = new Faculty(readerId, readerName, readerPhone, readerAddress);
        }

        libraryService.registerReader(reader);
    }

    private static void listAllReadersFlow(LibraryService libraryService) {
        List<Reader> readers = libraryService.getAllReaders();
        if (readers.isEmpty()) {
            System.out.println("No readers found.");
        } else {
            for (Reader reader : readers) {
                System.out.println(reader);
            }
        }
    }

    private static void borrowBookFlow(Reader reader,
                                       BookService bookService,
                                       BorrowService borrowService) {
        int recordId = InputUtil.readInt("Borrow record id: ");
        int bookId = InputUtil.readInt("Enter book id to borrow: ");
        double depositAmount = InputUtil.readDouble("Deposit amount: ");

        try {
            Book book = bookService.getBookById(bookId);
            borrowService.borrowBook(recordId, reader, book, depositAmount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void returnBookFlow(Reader reader,
                                       BookService bookService,
                                       BorrowService borrowService) {
        int bookId = InputUtil.readInt("Enter book id to return: ");

        try {
            Book book = bookService.getBookById(bookId);
            borrowService.returnBook(book, reader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMyBorrowedBooksFlow(Reader reader) {
        if (reader.getBorrowedBooks().isEmpty()) {
            System.out.println("You do not have any borrowed books.");
        } else {
            System.out.println("My Borrowed Books:");
            for (Book book : reader.getBorrowedBooks()) {
                System.out.println(book);
            }
        }
    }

    private static void showMyMemberInfoFlow(Reader reader, MemberService memberService) {
        System.out.println(reader);
        System.out.println(memberService.getMemberRecordByReaderId(reader.getId()));
    }
}