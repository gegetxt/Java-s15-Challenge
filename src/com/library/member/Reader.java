package com.library.member;

import com.library.book.Book;
import com.library.enums.MemberType;

import java.util.HashSet;
import java.util.Set;

public class Reader extends Person {

    private String phone;
    private String address;
    private MemberType memberType;
    private Set<Book> borrowedBooks;
    private int maxBookLimit;

    public Reader(int id, String name, String phone, String address, MemberType memberType, int maxBookLimit) {
        super(id, name);
        this.phone = phone;
        this.address = address;
        this.memberType = memberType;
        this.maxBookLimit = maxBookLimit;
        this.borrowedBooks = new HashSet<>();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.isBlank()) {
            this.phone = phone;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && !address.isBlank()) {
            this.address = address;
        }
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        if (memberType != null) {
            this.memberType = memberType;
        }
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        if (maxBookLimit > 0) {
            this.maxBookLimit = maxBookLimit;
        }
    }

    public boolean borrowBook(Book book) {
        if (book == null) {
            return false;
        }

        if (borrowedBooks.size() >= maxBookLimit) {
            return false;
        }

        return borrowedBooks.add(book);
    }

    public boolean returnBook(Book book) {
        if (book == null) {
            return false;
        }

        return borrowedBooks.remove(book);
    }

    public boolean hasReachedLimit() {
        return borrowedBooks.size() >= maxBookLimit;
    }

    @Override
    public void whoYouAre() {
        System.out.println("I am a reader. My name is " + getName());
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", memberType=" + memberType +
                ", borrowedBookCount=" + borrowedBooks.size() +
                ", maxBookLimit=" + maxBookLimit +
                '}';
    }
}
