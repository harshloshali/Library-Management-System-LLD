package org.assignment.entities;

import org.assignment.enums.Genre;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private Genre genre;

    private Book(Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.publicationYear = builder.publicationYear;
        this.genre = builder.genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public static class Builder {
        private final String isbn;
        private String title;
        private String author;
        private int publicationYear;
        private Genre genre;


        public Builder(String isbn) {
            this.isbn = isbn;
        }


        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder publicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (!(o instanceof Book)) return false;
        return isbn.equals(((Book)o).isbn);
    }

}
