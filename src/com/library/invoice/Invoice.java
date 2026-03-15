package com.library.invoice;

import com.library.book.Book;
import com.library.member.Reader;

import java.time.LocalDate;

public class Invoice {

    private int id;
    private Reader reader;
    private Book book;
    private double amount;
    private LocalDate invoiceDate;
    private boolean refunded;

    public Invoice(int id, Reader reader, Book book, double amount, LocalDate invoiceDate) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.refunded = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        if (reader != null) {
            this.reader = reader;
        }
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book != null) {
            this.book = book;
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount;
        }
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        if (invoiceDate != null) {
            this.invoiceDate = invoiceDate;
        }
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void markAsRefunded() {
        this.refunded = true;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", reader=" + reader.getName() +
                ", book=" + book.getTitle() +
                ", amount=" + amount +
                ", invoiceDate=" + invoiceDate +
                ", refunded=" + refunded +
                '}';
    }
}
