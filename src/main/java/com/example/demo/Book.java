package com.example.demo;

public class Book {
    private String name;
    private String author;
    private String genre;
    private int year;
    private int pagesCount;

    public Book(String name, String author, String genre, int year, int pagesCount){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.pagesCount = pagesCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }
}
