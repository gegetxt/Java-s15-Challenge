package com.library.borrow;

import com.library.book.Book;
import com.library.invoice.Invoice;
import com.library.invoice.InvoiceService;
import com.library.library.Library;
import com.library.member.MemberRecord;
import com.library.member.MemberService;
import com.library.member.Reader;

import java.time.LocalDate;
import java.util.List;

public class BorrowService {

    private BorrowRepository borrowRepository;
    private InvoiceService invoiceService;
    private MemberService memberService;
    private Library library;

    public BorrowService(BorrowRepository borrowRepository,
                         InvoiceService invoiceService,
                         MemberService memberService,
                         Library library) {
        this.borrowRepository = borrowRepository;
        this.invoiceService = invoiceService;
        this.memberService = memberService;
        this.library = library;
    }

    public void borrowBook(int recordId, Reader reader, Book book, double depositAmount) {
        if (reader == null || book == null) {
            System.out.println("Reader or book cannot be null.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("This book is already borrowed by someone else.");
            return;
        }

        MemberRecord memberRecord = memberService.getMemberRecordByReaderId(reader.getId());
        if (memberRecord == null) {
            System.out.println("Member record not found.");
            return;
        }

        if (!memberRecord.canBorrowMoreBooks()) {
            System.out.println("Reader has reached the maximum book limit or membership is inactive.");
            return;
        }

        List<BorrowRecord> activeRecords = borrowRepository.findActiveBorrowRecordsByReader(reader);
        if (activeRecords.size() >= 5) {
            System.out.println("Reader has reached the maximum book limit.");
            return;
        }

        BorrowRecord borrowRecord = new BorrowRecord(recordId, book, reader, LocalDate.now());
        borrowRepository.save(borrowRecord);

        book.markAsBorrowed();
        reader.borrowBook(book);
        memberService.updateBorrowedBookCount(reader.getId(), true);
        library.addBorrowRecord(borrowRecord);

        Invoice invoice = invoiceService.createInvoice(reader, book, depositAmount);

        System.out.println("Book borrowed successfully.");
        System.out.println("Borrow Record: " + borrowRecord);
        System.out.println("Invoice created: " + invoice);
    }

    public void returnBook(Book book, Reader reader) {
        if (book == null || reader == null) {
            System.out.println("Book or reader cannot be null.");
            return;
        }

        BorrowRecord activeRecord = borrowRepository.findActiveBorrowRecordByBook(book);

        if (activeRecord == null) {
            System.out.println("There is no active borrow record for this book.");
            return;
        }

        if (activeRecord.getReader().getId() != reader.getId()) {
            System.out.println("This book does not belong to this reader.");
            return;
        }

        activeRecord.markAsReturned(LocalDate.now());
        book.markAsAvailable();
        reader.returnBook(book);
        memberService.updateBorrowedBookCount(reader.getId(), false);
        invoiceService.refundInvoice(reader, book);

        System.out.println("Book returned successfully.");
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRepository.findAll();
    }

    public List<BorrowRecord> getBorrowRecordsByReader(Reader reader) {
        return borrowRepository.findByReader(reader);
    }

    public BorrowRecord getActiveBorrowRecordByBook(Book book) {
        return borrowRepository.findActiveBorrowRecordByBook(book);
    }

    public boolean isBookCurrentlyBorrowed(Book book) {
        return borrowRepository.findActiveBorrowRecordByBook(book) != null;
    }
}