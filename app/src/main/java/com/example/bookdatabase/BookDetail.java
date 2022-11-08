package com.example.bookdatabase;

public class BookDetail {

    private String bookName;
    private String bookGenre;
    private String bookPrice;
    public BookDetail() {
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookGenre() { return bookGenre; }
    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
