package com.library.book;

import com.library.enums.BookStatus;

public class Book {

    private int id;
    private String title;
    private Author author;
    private String category;
    private String isbn;
    private double price;
    private BookStatus status;

    public Book(int id, String title, Author author, String category, String isbn, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.price = price;
        this.status = BookStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        if (author != null) {
            this.author = author;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category != null && !category.isBlank()) {
            this.category = category;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.isBlank()) {
            this.isbn = isbn;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public void markAsBorrowed() {
        this.status = BookStatus.BORROWED;
    }

    public void markAsAvailable() {
        this.status = BookStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author.getName() +
                ", category='" + category + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}