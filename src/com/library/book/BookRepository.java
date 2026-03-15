package com.library.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    private Map<Integer, Book> books;

    public BookRepository() {
        this.books = new HashMap<>();
    }

    public void save(Book book) {
        if (book != null) {
            books.put(book.getId(), book);
        }
    }

    public Book findById(int id) {
        return books.get(id);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public boolean deleteById(int id) {
        return books.remove(id) != null;
    }

    public boolean existsById(int id) {
        return books.containsKey(id);
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();

        if (title == null || title.isBlank()) {
            return result;
        }

        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }

        return result;
    }

    public List<Book> findByAuthorName(String authorName) {
        List<Book> result = new ArrayList<>();

        if (authorName == null || authorName.isBlank()) {
            return result;
        }

        for (Book book : books.values()) {
            if (book.getAuthor() != null &&
                    book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }

        return result;
    }

    public List<Book> findByCategory(String category) {
        List<Book> result = new ArrayList<>();

        if (category == null || category.isBlank()) {
            return result;
        }

        for (Book book : books.values()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }

        return result;
    }
}
