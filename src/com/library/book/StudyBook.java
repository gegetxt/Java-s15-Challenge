package com.library.book;

public class StudyBook extends Book {

    private String subject;
    private int pageCount;

    public StudyBook(int id, String title, Author author, String category, String isbn, double price,
                     String subject, int pageCount) {
        super(id, title, author, category, isbn, price);
        this.subject = subject;
        this.pageCount = pageCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject != null && !subject.isBlank()) {
            this.subject = subject;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        }
    }

    @Override
    public String toString() {
        return "StudyBook{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author=" + getAuthor().getName() +
                ", category='" + getCategory() + '\'' +
                ", isbn='" + getIsbn() + '\'' +
                ", price=" + getPrice() +
                ", status=" + getStatus() +
                ", subject='" + subject + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}