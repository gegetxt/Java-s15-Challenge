package com.library.book;

public class Journal extends Book {

    private int issueNumber;
    private String publicationDate;

    public Journal(int id, String title, Author author, String category, String isbn, double price,
                   int issueNumber, String publicationDate) {
        super(id, title, author, category, isbn, price);
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        }
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        if (publicationDate != null && !publicationDate.isBlank()) {
            this.publicationDate = publicationDate;
        }
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author=" + getAuthor().getName() +
                ", category='" + getCategory() + '\'' +
                ", isbn='" + getIsbn() + '\'' +
                ", price=" + getPrice() +
                ", status=" + getStatus() +
                ", issueNumber=" + issueNumber +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}