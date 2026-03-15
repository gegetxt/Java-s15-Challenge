package com.library.book;

import com.library.exception.BookNotFoundException;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        if (book == null) {
            System.out.println("Book cannot be null.");
            return;
        }

        if (bookRepository.existsById(book.getId())) {
            System.out.println("A book with this id already exists.");
            return;
        }

        bookRepository.save(book);

        if (book.getAuthor() != null) {
            book.getAuthor().addBook(book);
        }

        System.out.println("Book added successfully.");
    }

    public Book getBookById(int id) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }

        return book;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public void updateBook(int id, String newTitle, Author newAuthor, String newCategory, String newIsbn, double newPrice) {
        Book book = getBookById(id);

        Author oldAuthor = book.getAuthor();

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setCategory(newCategory);
        book.setIsbn(newIsbn);
        book.setPrice(newPrice);

        if (oldAuthor != null && oldAuthor != newAuthor) {
            oldAuthor.removeBook(book);
        }

        if (newAuthor != null && newAuthor != oldAuthor) {
            newAuthor.addBook(book);
        }

        bookRepository.save(book);
        System.out.println("Book updated successfully.");
    }

    public void deleteBook(int id) {
        Book book = getBookById(id);

        if (!book.isAvailable()) {
            System.out.println("Borrowed books cannot be deleted.");
            return;
        }

        if (book.getAuthor() != null) {
            book.getAuthor().removeBook(book);
        }

        boolean deleted = bookRepository.deleteById(id);

        if (deleted) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book could not be deleted.");
        }
    }
}
