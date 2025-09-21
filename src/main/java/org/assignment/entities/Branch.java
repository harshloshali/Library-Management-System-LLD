package org.assignment.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    private final String id;
    private String name;
    private final Map<String, InventoryItem> inventory = new HashMap<>();
    private final Reservation reservation = new Reservation();

    public Branch(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId(){ return id; }
    public String getName(){ return name; }
    public void setName(String n){ this.name = n; }


    public void addBookCopies(Book book, int copies){
        inventory.compute(book.getIsbn(), (isbn, item) -> {
            if (item == null) return new InventoryItem(book, copies);
            item.addCopies(copies);
            return item;
        });
     }

    public boolean removeBookCopies(String isbn, int copies){
        InventoryItem item = inventory.get(isbn);
        if (item == null) return false;
        boolean ok = item.removeCopies(copies);
        if (!ok) return false;
        if (item.getTotalCopies() == 0) inventory.remove(isbn);
        return true;
    }

    public InventoryItem getInventoryItem(String isbn){ return inventory.get(isbn); }

    public boolean checkoutBook(String isbn, Patron p){
        InventoryItem item = inventory.get(isbn);
        if (item == null) return false;
        boolean ok = item.borrowOne();
        if (ok) p.borrow(item.getBook());
        return ok;
    }

    public boolean returnBook(String isbn, Patron p){
        InventoryItem item = inventory.get(isbn);
        if (item == null) return false;
        boolean ok = item.returnOne();
        if (ok) {
            p.returned(item.getBook());
            if (reservation.hasWaitlist(isbn)){
                reservation.notifyNext(isbn, item.getBook(), id);
            }
        }
        return ok;
    }

    public void reserveBook(String isbn, Patron p){
        reservation.reserve(isbn, p);
    }


    public List<InventoryItem> listInventory(){
        return new ArrayList<>(inventory.values());
    }
}
