package org.assignment.entities;

public class InventoryItem {

        private final Book book;
        private int totalCopies;
        private int borrowedCopies;

        public InventoryItem(Book book, int copies) {
            this.book =book;
            this.totalCopies= copies;
            this.borrowedCopies =0;
        }
        public Book getBook(){ return book; }
        public int getAvailableCopies(){
            return totalCopies - borrowedCopies;
        }
        public int getTotalCopies(){
            return totalCopies;
        }
        public void addCopies(int n){
            totalCopies +=n;
        }
        public boolean borrowOne(){
            if (getAvailableCopies() <= 0) return false;
            borrowedCopies++;
            return true;
        }
        public boolean returnOne(){
            if (borrowedCopies <= 0) return false;
            borrowedCopies--;
            return true;
        }
        public boolean removeCopies(int n){
            if (n > getAvailableCopies()) return false;
            totalCopies -= n;
            return true;
        }



}
