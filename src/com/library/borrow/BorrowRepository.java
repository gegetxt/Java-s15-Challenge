package com.library.borrow;

import com.library.book.Book;
import com.library.member.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowRepository {

    private Map<Integer, BorrowRecord> borrowRecords;

    public BorrowRepository() {
        this.borrowRecords = new HashMap<>();
    }

    public void save(BorrowRecord borrowRecord) {
        if (borrowRecord != null) {
            borrowRecords.put(borrowRecord.getId(), borrowRecord);
        }
    }

    public BorrowRecord findById(int id) {
        return borrowRecords.get(id);
    }

    public List<BorrowRecord> findAll() {
        return new ArrayList<>(borrowRecords.values());
    }

    public boolean deleteById(int id) {
        return borrowRecords.remove(id) != null;
    }

    public boolean existsById(int id) {
        return borrowRecords.containsKey(id);
    }

    public List<BorrowRecord> findByReader(Reader reader) {
        List<BorrowRecord> result = new ArrayList<>();

        if (reader == null) {
            return result;
        }

        for (BorrowRecord record : borrowRecords.values()) {
            if (record.getReader().getId() == reader.getId()) {
                result.add(record);
            }
        }

        return result;
    }

    public List<BorrowRecord> findByBook(Book book) {
        List<BorrowRecord> result = new ArrayList<>();

        if (book == null) {
            return result;
        }

        for (BorrowRecord record : borrowRecords.values()) {
            if (record.getBook().getId() == book.getId()) {
                result.add(record);
            }
        }

        return result;
    }

    public BorrowRecord findActiveBorrowRecordByBook(Book book) {
        if (book == null) {
            return null;
        }

        for (BorrowRecord record : borrowRecords.values()) {
            if (record.getBook().getId() == book.getId() && !record.isReturned()) {
                return record;
            }
        }

        return null;
    }

    public List<BorrowRecord> findActiveBorrowRecordsByReader(Reader reader) {
        List<BorrowRecord> result = new ArrayList<>();

        if (reader == null) {
            return result;
        }

        for (BorrowRecord record : borrowRecords.values()) {
            if (record.getReader().getId() == reader.getId() && !record.isReturned()) {
                result.add(record);
            }
        }

        return result;
    }
}