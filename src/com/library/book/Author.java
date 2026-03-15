package com.library.book;

import com.library.member.Person;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

    private List<Book> books;

    public Author(int id, String name) {
        super(id, name);
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getNumberOfBooks() {
        return books.size();
    }

    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public void whoYouAre() {
        System.out.println("I am an author. My name is " + getName());
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", numberOfBooks=" + getNumberOfBooks() +
                '}';
    }
}