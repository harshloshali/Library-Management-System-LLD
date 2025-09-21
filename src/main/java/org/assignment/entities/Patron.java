package org.assignment.entities;

import org.assignment.enums.Genre;

import java.util.*;

public class Patron implements PatronObserver{

    private final String id;
    private String name;
    private final Map<Genre, Integer> borrowingHistory = new LinkedHashMap<>();

    private final List<Book> currentlyBorrowed = new ArrayList<>();


    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setBorrowingHistory(Genre bookType){
        borrowingHistory.putIfAbsent(bookType,1);


    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Genre, Integer> getBorrowingHistory() {
        return borrowingHistory;
    }

    public List<Book> getCurrentlyBorrowed() { return Collections.unmodifiableList(currentlyBorrowed); }

        public void borrow(Book book) {
            currentlyBorrowed.add(book);
            setBorrowingHistory(book.getGenre());
        }
        public void returned(Book book) {
            currentlyBorrowed.remove(book);
        }

        @Override
        public void notifyBookAvailable(String branchId, Book book) {
            String msg = String.format("Book '%s' is now available at branch '%s'", book,branchId);

        }



}
