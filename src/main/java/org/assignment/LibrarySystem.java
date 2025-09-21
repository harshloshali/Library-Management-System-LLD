package org.assignment;

import org.assignment.entities.Branch;
import org.assignment.entities.InventoryItem;
import org.assignment.entities.Patron;
import org.assignment.entities.Book;

import java.util.*;
import java.util.logging.Logger;

public class LibrarySystem {
        private final Map<String, Branch> branches = new HashMap<>();
        private final Map<String, Patron> patrons = new HashMap<>();
        private final Map<String, Book> masterCatalog = new HashMap<>();
        private final Map<String, List<Book>> globalBorrowHistory = new HashMap<>();
        private final Logger logger = Logger.getLogger(LibrarySystem.class.getName());
        private RecommendationStrategy recommendationStrategy = new HistoryBasedRecommendation();


        public void addBranch(Branch branch){
            branches.put(branch.getId(), branch);
        }
        public Branch getBranch(String id){ return branches.get(id); }
        public Collection<Branch> listBranches(){ return branches.values(); }

        public void addPatron(Patron p){
            patrons.put(p.getId(), p);
            globalBorrowHistory.putIfAbsent(p.getId(), new ArrayList<>());

        }
        public Patron getPatron(String id){ return patrons.get(id); }

        public void registerBook(Book book){
            masterCatalog.putIfAbsent(book.getIsbn(), book);
        }
        public Book findBookByIsbn(String isbn){ return masterCatalog.get(isbn); }
        public Collection<Book> getAllBooks(){ return masterCatalog.values(); }


        public void addBookToBranch(String branchId, String isbn, int copies){
            Branch b = branches.get(branchId);
            Book book = masterCatalog.get(isbn);
            if (b == null || book == null) throw new IllegalArgumentException("Branch or book not found");
            b.addBookCopies(book, copies);
        }


        public boolean checkout(String branchId, String isbn, String patronId){
            Branch b = branches.get(branchId);
            Patron p = patrons.get(patronId);
            if (b == null || p == null) {

                return false;
            }
            boolean ok = b.checkoutBook(isbn, p);
            if (ok) {
                globalBorrowHistory.get(patronId).add(findBookByIsbn(isbn));
            } else{

            }
            return ok;
        }

        public boolean returnBook(String branchId, String isbn, String patronId){
            Branch b = branches.get(branchId);
            Patron p = patrons.get(patronId);
            if (b == null || p == null) {
                 return false;
            }
            return b.returnBook(isbn, p);
        }


        public void reserveBook(String branchId, String isbn, String patronId){
            Branch b = branches.get(branchId);
            Patron p = patrons.get(patronId);
            if (b == null || p == null) {
                return;
            }
            b.reserveBook(isbn, p);
        }

        public boolean transferCopies(String sourceBranchId, String targetBranchId, String isbn, int copies){
            Branch src = branches.get(sourceBranchId);
            Branch dst = branches.get(targetBranchId);
            Book book = masterCatalog.get(isbn);
            if (src == null || dst == null || book == null) {
                return false;
            }
            boolean removed = src.removeBookCopies(isbn, copies);
            if (!removed) {
                return false;
            }
            dst.addBookCopies(book, copies);
            return true;
        }

        public void printAllInventories(){
            System.out.println("=== ====All Branch Inventories ===");
            for (Branch b : branches.values()) {
                System.out.println(b);
                for (InventoryItem it : b.listInventory()){
                System.out.println("  " + it);
                }
            }
        }


}
