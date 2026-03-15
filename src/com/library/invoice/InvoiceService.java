package com.library.invoice;

import com.library.book.Book;
import com.library.member.Reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceService {

    private Map<Integer, Invoice> invoices;
    private int nextInvoiceId;

    public InvoiceService() {
        this.invoices = new HashMap<>();
        this.nextInvoiceId = 1;
    }

    public Invoice createInvoice(Reader reader, Book book, double amount) {
        if (reader == null || book == null) {
            System.out.println("Reader or book cannot be null.");
            return null;
        }

        if (amount < 0) {
            System.out.println("Amount cannot be negative.");
            return null;
        }

        Invoice invoice = new Invoice(nextInvoiceId++, reader, book, amount, LocalDate.now());
        invoices.put(invoice.getId(), invoice);

        return invoice;
    }

    public Invoice findById(int id) {
        return invoices.get(id);
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices.values());
    }

    public List<Invoice> getInvoicesByReader(Reader reader) {
        List<Invoice> result = new ArrayList<>();

        if (reader == null) {
            return result;
        }

        for (Invoice invoice : invoices.values()) {
            if (invoice.getReader().getId() == reader.getId()) {
                result.add(invoice);
            }
        }

        return result;
    }

    public List<Invoice> getInvoicesByBook(Book book) {
        List<Invoice> result = new ArrayList<>();

        if (book == null) {
            return result;
        }

        for (Invoice invoice : invoices.values()) {
            if (invoice.getBook().getId() == book.getId()) {
                result.add(invoice);
            }
        }

        return result;
    }

    public Invoice findActiveInvoice(Reader reader, Book book) {
        if (reader == null || book == null) {
            return null;
        }

        for (Invoice invoice : invoices.values()) {
            if (invoice.getReader().getId() == reader.getId()
                    && invoice.getBook().getId() == book.getId()
                    && !invoice.isRefunded()) {
                return invoice;
            }
        }

        return null;
    }

    public void refundInvoice(Reader reader, Book book) {
        Invoice invoice = findActiveInvoice(reader, book);

        if (invoice == null) {
            System.out.println("No active invoice found for this reader and book.");
            return;
        }

        invoice.markAsRefunded();
        System.out.println("Invoice refunded successfully.");
    }
}