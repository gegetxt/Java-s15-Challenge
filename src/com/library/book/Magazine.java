package com.library.book;

public class Magazine extends Book {

    private int issueNumber;
    private String publisher;

    public Magazine(int id, String title, Author author, String category, String isbn, double price,
                    int issueNumber, String publisher) {
        super(id, title, author, category, isbn, price);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.isBlank()) {
            this.publisher = publisher;
        }
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author=" + getAuthor().getName() +
                ", category='" + getCategory() + '\'' +
                ", isbn='" + getIsbn() + '\'' +
                ", price=" + getPrice() +
                ", status=" + getStatus() +
                ", issueNumber=" + issueNumber +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}